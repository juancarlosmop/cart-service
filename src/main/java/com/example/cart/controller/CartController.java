package com.example.cart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cart.model.Cart;
import com.example.cart.model.Payment;
import com.example.cart.model.RpBase;
import com.example.cart.model.RpTotalCart;
import com.example.cart.model.RqCart;
import com.example.cart.model.RqCartItem;
import com.example.cart.model.RqPayment;
import com.example.cart.service.ICartService;
@Controller
@RequestMapping("/cart")
public class CartController {
	
	
	
	@Autowired
	private ICartService cartService;
	
	//NOTA PROBAR ESTE SERVICIO PARA DESPUES CALAR EN LA ENTIDAD CAR LOS CARITEM
	@GetMapping(value="/get-cart/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cart> getCarritoById(@PathVariable("id") int id  ){
		HttpStatus http = HttpStatus.OK;
		Cart carResponse=cartService.getCarById(id);
		return new ResponseEntity<>(carResponse,http);
	}
	@PostMapping(value="/create-car",produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RpBase> createProduct(@Valid @RequestBody RqCart cart  ){
		RpBase rpBase = new RpBase();
		HttpStatus http = HttpStatus.CREATED;
		cartService.createCar(cart);
		rpBase.setCode("OK");
		rpBase.setMessage("The cart was created");
		
		return new ResponseEntity<>(rpBase,http);
	}
	
	@GetMapping(value="/get-total/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RpTotalCart> getTotalCarrito(@PathVariable("id") int id  ){
		RpTotalCart rqTotalCart = new RpTotalCart();
		HttpStatus http = HttpStatus.OK;
		double total = cartService.totalCarrito(id);
		rqTotalCart.setCode("OK");
		rqTotalCart.setMessage("The total prices from this client");
		rqTotalCart.setTotal(total);
		
		return new ResponseEntity<>(rqTotalCart,http);
	}
	
	@GetMapping(value="/remove-item/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RpBase> removeItemCarrito(@PathVariable("id") int id  ){
		RpBase rpBase = new RpBase();
		HttpStatus http = HttpStatus.OK;
	    cartService.removeItemCart(id);
	    rpBase.setCode("OK");
	    rpBase.setMessage("The element was remove");
		
		return new ResponseEntity<>(rpBase,http);
	}
	
	
	@PostMapping(value="/add-element-cart",produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RpBase> addElementsCar(@Valid @RequestBody RqCartItem rqCartItem){
		RpBase rpBase = new RpBase();
		HttpStatus http = HttpStatus.CREATED;
		cartService.addItemToCart(rqCartItem);
		rpBase.setCode("OK");
		rpBase.setMessage("there is a new item add");
		
		return new ResponseEntity<>(rpBase,http);
	}
	
	
	@PostMapping(value="/update-element-cart",produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RpBase> updateElementsCart(@Valid @RequestBody RqCartItem rqCartItem){
		RpBase rpBase = new RpBase();
		HttpStatus http = HttpStatus.OK;
		cartService.updateItemCartById(rqCartItem);
		rpBase.setCode("OK");
		rpBase.setMessage("The items were updating");
		return new ResponseEntity<>(rpBase,http);
	}
	
	
	@PostMapping(value="/make-payment",produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RpBase> makePayment(@Valid @RequestBody RqPayment rqPayment  ){
		HttpStatus http = HttpStatus.CREATED;
		RpBase rpBase =cartService.savePayment(rqPayment);
		return new ResponseEntity<>(rpBase,http);
	}
	
	
	@GetMapping(value="/get-bill/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Payment> getBillById(@PathVariable("email") String email  ){
		HttpStatus http = HttpStatus.OK;
	    Payment  payment=cartService.getPaymentBillByEmail(email);
		return new ResponseEntity<>(payment,http);
	}
	
	
	
}

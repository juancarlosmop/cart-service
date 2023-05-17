package com.example.cart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cart.dto.RpBase;
import com.example.cart.dto.RpPayment;
import com.example.cart.dto.RpTotalCart;
import com.example.cart.dto.RqCart;
import com.example.cart.dto.RqCartItem;
import com.example.cart.dto.RqPayment;
import com.example.cart.model.Cart;
import com.example.cart.model.Payment;
import com.example.cart.service.ICartService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ICartService cartService;

	/*
	 * Method to get a cart by id
	 * 
	 * @param int id from cart
	 * 
	 * @return Cart response Entity
	 */
	@Operation(summary = "Get cart by Id")
	@GetMapping(value = "/get-cart/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cart> getCarrById(@PathVariable("id") int id) {
		HttpStatus http = HttpStatus.OK;
		log.info("Starting to get cart with ID {}",id);
		Cart carResponse = cartService.getCarById(id);
		log.info("end to get cart with ID {}",id);
		return new ResponseEntity<>(carResponse, http);
	}

	/*
	 * Method to create a new cart
	 * 
	 * @param RqCart request from Cart
	 * 
	 * @return RpBase Dto response
	 */
	@Operation(summary = "create car ")
	@PostMapping(value = "/create-car", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RpBase> createProduct(@Valid @RequestBody RqCart cart) {
		RpBase rpBase = new RpBase();
		HttpStatus http = HttpStatus.CREATED;
		log.info("Starting to create a cart");
		cartService.createCar(cart);
		rpBase.setCode("OK");
		rpBase.setMessage("The cart was created");
		log.info("Ending to create a cart");
		return new ResponseEntity<>(rpBase, http);
	}

	/*
	 * Method to get total from a car by id
	 * 
	 * @param int id cart
	 * 
	 * @return RpTotalCart Dto response with the total cart
	 */
	@Operation(summary = "Get total amount from one cart by id")
	@GetMapping(value = "/get-total/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RpTotalCart> getTotalCarrito(@PathVariable("id") int id) {
		RpTotalCart rqTotalCart = new RpTotalCart();
		HttpStatus http = HttpStatus.OK;
		log.info("Starting to get total cart by ID {}",id);
		double total = cartService.totalCarrito(id);
		rqTotalCart.setCode("OK");
		rqTotalCart.setMessage("The total prices from this client");
		rqTotalCart.setTotal(total);
		log.info("Ending to get total cart by ID {}",id);
		return new ResponseEntity<>(rqTotalCart, http);
	}

	/*
	 * Method to remove a item from a cart by id
	 * 
	 * @param int id
	 * 
	 * @return RpBase Dto response
	 */
	@Operation(summary = "Remove one element by id cart")
	@GetMapping(value = "/remove-item/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RpBase> removeItemCarrito(@PathVariable("id") int id) {
		RpBase rpBase = new RpBase();
		HttpStatus http = HttpStatus.OK;
		log.info("Starting to remove cart by ID {}",id);
		cartService.removeItemCart(id);
		rpBase.setCode("OK");
		rpBase.setMessage("The element was remove");
		log.info("Ending to remove cart by ID {}",id);
		return new ResponseEntity<>(rpBase, http);
	}

	/*
	 * Method to add a new item to cart
	 * 
	 * @param RqCartItem request from CartItem
	 * 
	 * @return RpBase Dto response
	 */
	@Operation(summary = "add new element a cart")
	@PostMapping(value = "/add-element-cart", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RpBase> addElementsCar(@Valid @RequestBody RqCartItem rqCartItem) {
		RpBase rpBase = new RpBase();
		HttpStatus http = HttpStatus.CREATED;
		log.info("Starting to add new elemen in cart ");
		cartService.addItemToCart(rqCartItem);
		rpBase.setCode("OK");
		rpBase.setMessage("there is a new item add");
		log.info("Ending to add new elemen in cart");
		return new ResponseEntity<>(rpBase, http);
	}

	/*
	 * Method to update a new CartItem
	 * 
	 * @param RqCartItem request from CartItem
	 * 
	 * @return RpBase Dto response
	 */
	@Operation(summary = "update a  element from cart")
	@PostMapping(value = "/update-element-cart", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RpBase> updateElementsCart(@Valid @RequestBody RqCartItem rqCartItem) {
		RpBase rpBase = new RpBase();
		HttpStatus http = HttpStatus.OK;
		log.info("Starting to update a element from cart");
		cartService.updateItemCartById(rqCartItem);
		log.info("Ending to update a element from cart");
		rpBase.setCode("OK");
		rpBase.setMessage("The items were updating");
		return new ResponseEntity<>(rpBase, http);
	}

	/*
	 * Method to make a payment
	 * 
	 * @param RqPayment request from Payment
	 * 
	 * @return RpBase Dto response
	 */
	@Operation(summary = "To make a payment")
	@PostMapping(value = "/make-payment", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RpPayment> makePayment(@Valid @RequestBody RqPayment rqPayment) {
		HttpStatus httpStatus = HttpStatus.CREATED;
		log.info("Starting to make a payment");
		RpPayment rpPaymentProces=cartService.savePayment(rqPayment);
		log.info("Ending to make a payment");
		return new ResponseEntity<>(rpPaymentProces, httpStatus);
	}

	/*
	 * Method to get a bill payment by email user
	 * 
	 * @param eamil user
	 * 
	 * @return Payment response a Payment
	 */
	@Operation(summary = "get bill by email")
	@GetMapping(value = "/get-bill/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Payment> getBillById(@PathVariable("email") String email) {
		HttpStatus http = HttpStatus.OK;
		log.info("Starting to get a bill by email");
		Payment payment = cartService.getPaymentBillByEmail(email);
		log.info("Ending to get a bill by email");
		return new ResponseEntity<>(payment, http);
	}

}

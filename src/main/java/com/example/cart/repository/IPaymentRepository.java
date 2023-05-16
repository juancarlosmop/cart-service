package com.example.cart.repository;


import com.example.cart.dto.RqPayment;
import com.example.cart.model.Payment;

/*
 * Interface to manage the operations entity Payment
 * 
 * @author JC
 * */
public interface IPaymentRepository {
	
	/* Method to save a payment
	 * 
	 * @param  RqPayment payment request from Payment
	 * @return int when the record was save
	 * */
	public int savePayment(RqPayment payment);
	
	/* Method to get the bill from payment by id
	 * 
	 * @param String email from User 
	 * @return  Payment
	 * */
	public Payment getBillPaymentByEmail(String email );
}

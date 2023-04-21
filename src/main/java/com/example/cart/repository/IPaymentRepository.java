package com.example.cart.repository;


import com.example.cart.model.Payment;
import com.example.cart.model.RqPayment;

public interface IPaymentRepository {
	public int savePayment(RqPayment payment);
	public Payment getBillPaymentByEmail(String email );
}

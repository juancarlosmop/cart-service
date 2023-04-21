package com.example.cart.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RpPayment extends RpBase implements Serializable {
	@JsonProperty("items_reject")
	List<CartItem> itemsReject;

}

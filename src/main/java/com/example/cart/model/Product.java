package com.example.cart.model;

import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product  implements Serializable {
    
	private int id;
    private String name;
    private double price;
    private String description;
    private int stock;

}

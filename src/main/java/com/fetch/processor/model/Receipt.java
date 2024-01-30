package com.fetch.processor.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
	
	private String retailer;
	
	private LocalDate purchaseDate;
	
	private LocalTime purchaseTime;
	
	private List<Item> items;
	
	private double total;
}

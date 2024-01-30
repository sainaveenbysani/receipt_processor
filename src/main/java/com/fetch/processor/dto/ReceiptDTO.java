package com.fetch.processor.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fetch.processor.model.Item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {
	
	private String retailer;
	
	private String purchaseDate;
	
	private String purchaseTime;
	
	@NotNull
	@Valid
	@Size(min=1)
	private List<ItemDTO> items;
	
	@NotBlank
	private String total;
}

package com.fetch.processor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
	
	@NotBlank
	private String shortDescription;
	
	@NotBlank
	private String price;

}

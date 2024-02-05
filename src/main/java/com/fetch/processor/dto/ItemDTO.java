package com.fetch.processor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
	
    @NotEmpty
    @Pattern(regexp = "^[\\w\\s\\&\\-]+$")
    private String shortDescription;

    @NotEmpty
    @Pattern(regexp = "^\\d+\\.\\d{2}$")
    private String price;

}

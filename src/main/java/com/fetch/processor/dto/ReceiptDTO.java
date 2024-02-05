package com.fetch.processor.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {
    @NotEmpty
    @Pattern(regexp = "^[\\w\\s\\&\\-]+$")
    private String retailer;

    @NotEmpty
    private String purchaseDate;

    @NotEmpty
    private String purchaseTime;

    @Valid
    @NotEmpty
    @Size(min=1)
    private List<ItemDTO> items;

    @NotEmpty
    @Pattern(regexp = "^\\d+\\.\\d{2}$")
    private String total;
}


package com.fetch.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import jakarta.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fetch.processor.controller.ReceiptProcessController;
import com.fetch.processor.dto.ItemDTO;
import com.fetch.processor.dto.ProcessResponseDTO;
import com.fetch.processor.dto.ReceiptDTO;
import com.fetch.processor.service.ReceiptProcessService;

@SpringBootTest
public class ReceiptProcessorApplicationTests {

    @Autowired
    private ReceiptProcessController controller;

    @MockBean
    private ReceiptProcessService service;

    private ReceiptDTO receipt;

    @BeforeEach
    public void setUp() {
        receipt = new ReceiptDTO();
    }

    @Test
    public void testProcessReceipt() {

        ItemDTO item1 = createItemDTO("1.12", "Test Receipt");
        ItemDTO item2 = createItemDTO("1.15", "Rewards");
        List<ItemDTO> items = List.of(item1, item2);

        receipt.setPurchaseDate("2018-06-24");
        receipt.setPurchaseTime("15:00");
        receipt.setRetailer("Fetch");
        receipt.setTotal("12.25");
        receipt.setItems(items);

        when(service.processReceipt(any(ReceiptDTO.class))).thenReturn("mockedId");
        ResponseEntity<ProcessResponseDTO> responseEntity = controller.process(receipt);
        verify(service).processReceipt(eq(receipt));
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
        assertEquals("mockedId", responseEntity.getBody().getId());
    }

    private ItemDTO createItemDTO(String price, String description) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(price);
        itemDTO.setShortDescription(description);
        return itemDTO;
    }
    
    @Test
    public void testRetailerInvalidArgument() {
    	
    	ItemDTO item1 = createItemDTO("1.12", "Test Receipt");
        ItemDTO item2 = createItemDTO("1.15", "Rewards");
        List<ItemDTO> items = List.of(item1, item2);

        receipt.setPurchaseDate("2018-06-24");
        receipt.setPurchaseTime("15:00");
        receipt.setRetailer("^Fetch");
        receipt.setTotal("12.25");
        receipt.setItems(items);

        assertThrows(ConstraintViolationException.class, () -> {
            controller.process(receipt);
        });
    }
    
    @Test
    public void testItemDescriptionInvalidArgument() {
    	
    	ItemDTO item1 = createItemDTO("1.12", "Test*Receipt");
        ItemDTO item2 = createItemDTO("1.15", "Rewards");
        List<ItemDTO> items = List.of(item1, item2);

        receipt.setPurchaseDate("2018-06-24");
        receipt.setPurchaseTime("15:00");
        receipt.setRetailer("Fetch");
        receipt.setTotal("12.25");
        receipt.setItems(items);

        assertThrows(ConstraintViolationException.class, () -> {
            controller.process(receipt);
        });
    }
}


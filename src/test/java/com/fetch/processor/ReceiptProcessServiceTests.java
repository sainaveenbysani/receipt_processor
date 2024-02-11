package com.fetch.processor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fetch.processor.dto.ItemDTO;
import com.fetch.processor.dto.ReceiptDTO;
import com.fetch.processor.model.Item;
import com.fetch.processor.service.ReceiptProcessService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ReceiptProcessServiceTests {

    private ReceiptProcessService receiptService;
    private ReceiptDTO receipt;
    
    @BeforeEach
    public void setUp() {
        receiptService = new ReceiptProcessService();
        receipt = new ReceiptDTO();
    }

    @Test
    public void testRetailerPoints() {
        assertEquals(9, receiptService.getRetailerPoints("Fetch1234"));
        assertEquals(7, receiptService.getRetailerPoints("  F_etch&Go"));
        assertEquals(12, receiptService.getRetailerPoints("10000rewards"));
        assertEquals(8, receiptService.getRetailerPoints("Fet_chi&ng   "));
    }

    @Test
    public void testTotalPoints() {
        assertEquals(0, receiptService.getTotalPoints(2.45));
        assertEquals(75, receiptService.getTotalPoints(1.00));
        assertEquals(25, receiptService.getTotalPoints(1.25));
        assertEquals(75, receiptService.getTotalPoints(100.00));
    }

    @Test
    public void testItemsPoints() {
        List<Item> items = new ArrayList<>();
        items.add(createItem(10.67, "Welcome to Fetch"));
        items.add(createItem(11.15, "Get more reward"));
        assertEquals(8, receiptService.getItemsPoints(items));

        items.clear();
        items.add(createItem(15.45, "Hi!"));
        items.add(createItem(12.24, "Good Morning"));
        items.add(createItem(1.16, "Welcome to Fetch"));
        items.add(createItem(0.16, "Fetch&"));
        assertEquals(18, receiptService.getItemsPoints(items));
    }

    @Test
    public void testReceiptProcessing() {
        receipt.setItems(List.of(createItemDTO("1.12", "Test Receipt"), createItemDTO("1.15", "Rewards")));
        receipt.setPurchaseDate("2015-12-06");
        receipt.setPurchaseTime("13:00");
        receipt.setRetailer("Fetch");
        receipt.setTotal("120");
        assertNotNull(receiptService.processReceipt(receipt));
    }

    @Test
    public void testPurchaseTimeInInterval() {
        assertEquals(10, receiptService.getPurchaseTime(LocalTime.parse("15:00")));
        assertEquals(10, receiptService.getPurchaseTime(LocalTime.parse("14:01")));
        assertEquals(10, receiptService.getPurchaseTime(LocalTime.parse("15:59")));
    }

    @Test
    public void testPurchaseTimeOutOfInterval() {
        assertEquals(0, receiptService.getPurchaseTime(LocalTime.parse("22:00")));
        assertEquals(0, receiptService.getPurchaseTime(LocalTime.parse("14:00")));
        assertEquals(0, receiptService.getPurchaseTime(LocalTime.parse("16:00")));
    }

    @Test
    public void testPurchaseDatePoints() {
        assertEquals(0, receiptService.getPurchaseDate(LocalDate.parse("2022-12-28")));
        assertEquals(6, receiptService.getPurchaseDate(LocalDate.parse("2017-07-31")));
    }

    private Item createItem(double price, String description) {
        Item item = new Item();
        item.setPrice(price);
        item.setShortDescription(description);
        return item;
    }

    private ItemDTO createItemDTO(String price, String description) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setPrice(price);
        itemDTO.setShortDescription(description);
        return itemDTO;
    }
}

package com.fetch.processor.mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.fetch.processor.dto.ItemDTO;
import com.fetch.processor.dto.ReceiptDTO;
import com.fetch.processor.model.Item;
import com.fetch.processor.model.Receipt;

public class ReceiptMapper {
	
	//private static final ModelMapper modelMapper = new ModelMapper();

//    public static Receipt convertToModel(ReceiptDTO receiptDTO) {
//        return modelMapper.map(receiptDTO, Receipt.class);
//    }
//    
    public static Receipt convertToModel(ReceiptDTO receiptDTO) {
        Receipt receipt = new Receipt();
        receipt.setRetailer(receiptDTO.getRetailer());
        receipt.setPurchaseDate(LocalDate.parse(receiptDTO.getPurchaseDate()));
        receipt.setPurchaseTime(LocalTime.parse(receiptDTO.getPurchaseTime()));
        List<Item> items = receiptDTO.getItems().stream()
                .map(ReceiptMapper::convertItemDTOToItem)
                .collect(Collectors.toList());
        receipt.setItems(items);
        receipt.setTotal(Double.parseDouble(receiptDTO.getTotal()));
        return receipt;
    }

    private static Item convertItemDTOToItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setShortDescription(itemDTO.getShortDescription());
        item.setPrice(Double.parseDouble(itemDTO.getPrice()));
        return item;
    }
}

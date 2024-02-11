package com.fetch.processor.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import com.fetch.processor.dto.ReceiptDTO;
import com.fetch.processor.exception.ReceiptNotFoundException;
import com.fetch.processor.mapper.ReceiptMapper;
import com.fetch.processor.model.Item;
import com.fetch.processor.model.Receipt;

@Service
public class ReceiptProcessService {
	
	Map<String, Receipt> receiptMap = new HashMap<>();
	
	public String processReceipt(ReceiptDTO receipt) {
		Receipt newReceipt= ReceiptMapper.convertToModel(receipt);
		//Need to implement some logic to find if the Receipt is already processed?
		String id = UUID.randomUUID().toString();
		receiptMap.put(id, newReceipt);
		return id;
	}
	
	public long getPoints(String id) {
		if(receiptMap.get(id)!= null) {
			Long points = 0L;
			Receipt	receipt = receiptMap.get(id);
			points = points + getRetailerPoints(receipt.getRetailer());
			points = points + getTotalPoints(receipt.getTotal());
			points = points + getItemsPoints(receipt.getItems());
			points = points + getPurchaseDate(receipt.getPurchaseDate());
			points = points + getPurchaseTime(receipt.getPurchaseTime());
			return points;
		}else {
			throw new ReceiptNotFoundException("No receipt found for the id : "+id);
		}
	}
	
	public Long getPurchaseTime(LocalTime purchaseTime) {
		long points=0L;
		if (purchaseTime.isAfter(LocalTime.parse("14:00")) && purchaseTime.isBefore(LocalTime.parse("16:00"))) {
            points = points + 10;
        }
		return points;
	}

	public Long getPurchaseDate(LocalDate purchaseDate) {
		long points = 0L;
		if(purchaseDate.getDayOfMonth()%2 !=0) {
			points = points + 6;
		}
		return points;
	}

	public Long getItemsPoints(List<Item> items) {
		long points = 0L;
		points = points + 5 *(items.size()/2);
		for(Item item : items) {
			int length = item.getShortDescription().trim().length();
			if(length%3==0) {
				points = (long) (points + Math.ceil(item.getPrice() * 0.2));
			}
		}
		return points;
	}

	public Long getTotalPoints(double total) {
		long points = 0L;
		if(total%0.25==0.00) {
			points = points +25;
		}
		if(total%1.00==0.00) {
			points = points + 50;
		}
		return points;
	}

	public long getRetailerPoints(String name) {
		long points = 0L;
		for(int i=0; i<name.length();i++){
			if(Character.isLetterOrDigit(name.charAt(i))){
				points++;
			}
		}
		return points;
	}
}

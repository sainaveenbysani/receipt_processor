package com.fetch.processor.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import com.fetch.processor.dto.MemoryMap;
import com.fetch.processor.dto.ReceiptDTO;
import com.fetch.processor.exception.ReceiptFoundException;
import com.fetch.processor.exception.ReceiptInvalidException;
import com.fetch.processor.exception.ReceiptNotFoundException;
import com.fetch.processor.mapper.ReceiptMapper;
import com.fetch.processor.model.Item;
import com.fetch.processor.model.Receipt;

@Service
public class ReceiptProcessService {
    
//	@Autowired
//	MemoryMap  map;
//	
	Map<String, Receipt> receiptMap = new HashMap<>();
	
	public String processReceipt(ReceiptDTO receipt) {
		boolean isReceiptValid = validateReceipt(receipt); //Implement Validation Logic
		if(!isReceiptValid) {
			throw new ReceiptInvalidException("The receipt is invalid");
		}
		Receipt newReceipt= ReceiptMapper.convertToModel(receipt);
		if(receiptMap.containsValue(newReceipt)) {
		   throw new ReceiptFoundException("The receipt is already scanned. please scan new receipt");
		}
		String id = UUID.randomUUID().toString();
		receiptMap.put(id, newReceipt);
		return id;
	}
//	
//	public Receipt processReceipt(ReceiptDTO receipt) {
//	boolean isReceiptValid = validateReceipt(receipt); //Implement Validation Logic
//	if(!isReceiptValid) {
//		throw new ReceiptInvalidException("The receipt is invalid");
//	}
//	Receipt newReceipt= ReceiptMapper.convertToModel(receipt);
//	if(receiptMap.containsValue(newReceipt)) {
//	   throw new ReceiptFoundException("The receipt is already scanned. please scan new receipt");
//	}
//	String id = UUID.randomUUID().toString();
//	receiptMap.put(id, newReceipt);
//	return newReceipt;
//   }
//	

	protected boolean validateReceipt(ReceiptDTO receipt) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		String value = receipt.getTotal();
		return value != null && value.matches("^\\d+\\.\\d{2}$");
		
		//return isValid;
	}

	//Requirements
	/*One point for every alphanumeric character in the retailer name.
50 points if the total is a round dollar amount with no cents.
25 points if the total is a multiple of 0.25.
5 points for every two items on the receipt.
If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
6 points if the day in the purchase date is odd.
10 points if the time of purchase is after 2:00pm and before 4:00pm.
*/
	/*
	 * {
    "id": {
        "retailer": "Target",
        "purchaseDate": "2022-01-01",
        "purchaseTime": "13:01:00",
        "items": [
            {
                "shortDescription": "Mountain Dew 12PK",
                "price": 6.49
            },
            {
                "shortDescription": "Emils Cheese Pizza",
                "price": 12.25
            },
            {
                "shortDescription": "Knorr Creamy Chicken",
                "price": 1.26
            },
            {
                "shortDescription": "Doritos Nacho Cheese",
                "price": 3.35
            },
            {
                "shortDescription": "Klarbrunn 12-PK 12 FL OZ",
                "price": 12.0
            }
        ],
        "total": 35.35
    }
}
	 */
	
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
	
	private Long getPurchaseTime(LocalTime purchaseTime) {
		long points=0L;
		//Check the end limit
		if(purchaseTime.getHour()>13 && purchaseTime.getHour()<16) {
			points = points + 10;
		}
		return points;
	}

	private Long getPurchaseDate(LocalDate purchaseDate) {
		long points = 0L;
		if(purchaseDate.getDayOfMonth()%2 !=0) {
			points = points + 6;
		}
		return points;
	}

	private Long getItemsPoints(List<Item> items) {
		long points = 0L;
		points = points + 5 * (int)(items.size()/2);
		for(Item item : items) {
			int length = item.getShortDescription().trim().length();
			if(length%3==0) {
				points = (long) (points + Math.ceil(item.getPrice() * 0.2));
			}
		}
		return points;
	}

	private Long getTotalPoints(double total) {
		long points = 0L;
		if(total%0.25==0.00) {
			points = points +25;
		}
		if(total%1.00==0.00) {
			points = points + 50;
		}
		return points;
	}

	private long getRetailerPoints(String name) {
		long points = 0L;
		for(int i=0; i<name.length();i++) {
			int value= name.charAt(i);
			if((value>=0&&value<=9)||(value>=65&&value<=90)||(value>=97&&value<=123)) {
				points++;
			}
		}
		return points;
	}

}

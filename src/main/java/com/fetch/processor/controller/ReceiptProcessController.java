package com.fetch.processor.controller;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fetch.processor.dto.ReceiptDTO;
import com.fetch.processor.model.Receipt;
import com.fetch.processor.service.ReceiptProcessService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/receipts")
public class ReceiptProcessController {
	
	@Autowired
	ReceiptProcessService receiptProcessService;

	@PostMapping("/process")
	public ResponseEntity<Map<String,String>> process(@Valid @RequestBody ReceiptDTO receipt){
		String id = receiptProcessService.processReceipt(receipt);
//		if(id!=null && !id.isEmpty()) {
			Map<String, String> map = new HashMap<>();
			map.put("id", id);
			return ResponseEntity.ok(map);
		//}	
	}
	
//	@PostMapping("/process")
//	public ResponseEntity<Map<String,Receipt>> process(@RequestBody ReceiptDTO receipt){
//		Receipt newReceipt= receiptProcessService.processReceipt(receipt);
////		if(id!=null && !id.isEmpty()) {
//			Map<String, Receipt> map = new HashMap<>();
//			map.put("id", newReceipt);
//			return ResponseEntity.ok(map);
//		//}	
//	}
	
	@GetMapping("/{id}/points")
	public ResponseEntity<Map<String,Long>> getPoints(@PathVariable(value="id") String id){
		long points = receiptProcessService.getPoints(id);
        Map<String, Long> map = new HashMap<>();
        map.put("points", points);
		return ResponseEntity.ok(map);	
	}
}

package com.fetch.processor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fetch.processor.dto.ReceiptDTO;
import com.fetch.processor.dto.PointsResponseDTO;
import com.fetch.processor.service.ReceiptProcessService;
import com.fetch.processor.dto.ProcessResponseDTO;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/receipts")
public class ReceiptProcessController {
	
	@Autowired
	ReceiptProcessService receiptProcessService;

	@PostMapping("/process")
	public ResponseEntity<ProcessResponseDTO> process(@Valid @RequestBody ReceiptDTO receipt){
		String id = receiptProcessService.processReceipt(receipt);
		ProcessResponseDTO response = new ProcessResponseDTO();
		response.setId(id);
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}/points")
	public ResponseEntity<PointsResponseDTO> getPoints(@PathVariable(value="id") String id){
		long points = receiptProcessService.getPoints(id);
		PointsResponseDTO response = new PointsResponseDTO();
		response.setPoints(points);
		return ResponseEntity.ok(response);	
	}
}

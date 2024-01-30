package com.fetch.processor.dto;

import org.springframework.stereotype.Component;

import com.fetch.processor.model.Receipt;

import java.util.Map;
import java.util.HashMap;

@Component
public class MemoryMap {
	
	Map<String, Receipt> map = new HashMap<>();

	public void put(String id, Receipt receipt) {
		map.put(id, receipt);
	}
	
	public Receipt get(String id) {
		return map.get(id);
	}
}

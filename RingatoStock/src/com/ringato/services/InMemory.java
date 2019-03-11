package com.ringato.services;

import java.util.ArrayList;
import java.util.List;

public class InMemory {
	
	private static InMemory instance;

	private static List<StockItem> stock = new ArrayList<>(); 
		
	private InMemory() {
		
	}
	
	public static InMemory getInstance() {
		if (instance == null) {
			instance = new InMemory();	
		}		
		return instance;
	}
	
	public void addToStock(StockItem item) {
		stock.add(item);		
	}

	public List<StockItem> getStock() {
		return stock;
	}

}

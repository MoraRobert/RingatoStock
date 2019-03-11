package model;

import com.ringato.services.StockItem;

public class Book extends StockItem{

	public Book(String title, int quantity) {
		super(title, quantity);
		
	}

	@Override
	public String toString() {
		
		return this.getTitle();
	}
	
	


}

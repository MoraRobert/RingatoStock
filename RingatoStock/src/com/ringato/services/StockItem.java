package com.ringato.services;

import java.util.concurrent.ThreadLocalRandom;

public abstract class StockItem {
		
	private final String title;
	private final long id;
	private String author;
	private String illustrator;
	private int quantity;
	private double price;
	
	public StockItem(String title, int quantity) {
		
		this.title = title;
		this.quantity = quantity;
		this.id = ThreadLocalRandom.current().nextInt(1000, 3000);;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setIllustrator(String illustrator) {
		this.illustrator = illustrator;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(String price) {
		this.price = Double.valueOf(price);
	}

	public String getAuthor() {
		return author;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getIllustrator() {
		return illustrator;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {

		return quantity + " pieces of " + title + " written by " + author +
				" and illustrated by " + illustrator + ", at the price of " + (int)price;
	}
		
	
	
}

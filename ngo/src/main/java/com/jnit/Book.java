package com.jnit;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
	@Id
	@Column(name = "bid")
	private int bid;
	@Column(name = "book_name")
	private String book_name;
	@Column(name = "book_price")
	private int book_price;
	@Column(name = "book_author")
	private String book_author;
	@Column(name = "book_publisher")
	private String book_publisher;
	public int getBid() {
		return bid;
	}
	
	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public int getBook_price() {
		return book_price;
	}
	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public String getBook_publisher() {
		return book_publisher;
	}
	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}
	
}

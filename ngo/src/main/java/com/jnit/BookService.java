package com.jnit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository bookReopsitory;
	
	public List<Book> select() {
		List<Book> list=bookReopsitory.findAll();
		return list;
	}
	
	public Book getBookbyId(int id) {
		return bookReopsitory.findById(id).get();
	}
	
	public void insert(Book book) {
		bookReopsitory.save(book);
	}
	
	public void update(Book book) {
		bookReopsitory.save(book);
	}
	
	public void delete(int id) {
		bookReopsitory.deleteById(id);
	}
}

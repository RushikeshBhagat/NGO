package com.jnit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RestController
public class BookController {

	@Autowired
	private BookService bookService;

	@Configuration
    public class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowCredentials(true);
        }
    }
	/*
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	*/
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	/*
	public String getallBooks(Model model) {
		
		List<Book> book=bookService.select();
		model.addAttribute("book",book);
		
		return "view";
		
	}
	*/
	public List<Book> getAllBooks(){
		return bookService.select();
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public void AddBook(@RequestBody Book book) {
		System.out.println("Received book: " + book.toString());
		bookService.insert(book);
	}
	/*
	public String addBooks(Model model) {
		Book book=new Book();
		model.addAttribute("book",book);
		return "addBook";
	}
	*/
	
	/*
	@PostMapping("/add")
	public String saveBook(@ModelAttribute("book") Book book) {
		bookService.insert(book);
		return "redirect:/view";
	}
	*/
	
	@RequestMapping(value = "/updateBook/{bid}", method = RequestMethod.GET)
	public Book updateBook(@PathVariable int bid) {
		//model.addAttribute("book",bookService.getBookbyId(bid));
		Book book=bookService.getBookbyId(bid);
		return bookService.getBookbyId(bid);
		//return "updateBook";
	}
	
	@RequestMapping(value = "/update/{bid}", method = RequestMethod.PUT)
	public void editBook(@PathVariable int bid, @RequestBody Book book) {
		Book existingBook=bookService.getBookbyId(bid);
		//existingBook.setBid(bid);
		existingBook.setBook_name(book.getBook_name());
		existingBook.setBook_price(book.getBook_price());
		existingBook.setBook_author(book.getBook_author());
		existingBook.setBook_publisher(book.getBook_publisher());
		
		bookService.update(existingBook);
		//return "redirect:/view";
	}
	
	@RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void DeleteBook(@PathVariable int id) {
		bookService.delete(id);
	}
	
}

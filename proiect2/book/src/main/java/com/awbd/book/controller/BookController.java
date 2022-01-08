package com.awbd.book.controller;

import com.awbd.book.DiscountServiceProxy;
import com.awbd.book.model.Book;
import com.awbd.book.model.Discount;
import com.awbd.book.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@Slf4j
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    DiscountServiceProxy discountServiceProxy;


    @GetMapping("/")
    String welcome(){
        return "Welcome to our library!";
    }

    @GetMapping("/book/all")
    public List<Book> getAllBooks(){

        return bookService.getAllBooks();
    }

    @GetMapping("/book/title/{title}/publisher/{publisher}")
    public Book findByPublisherAndTitle(@PathVariable String title,
                             @PathVariable String publisher){
        Book book = bookService.findByPublisherAndTitle(title, publisher);
        return book;
    }

    @PostMapping("/book")
    public ResponseEntity<Book> save(@Valid @RequestBody Book book){
        Book savedBook = bookService.save(book);
        URI locationUri =ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{bookId}").buildAndExpand(savedBook.getId())
                .toUri();


        return ResponseEntity.created(locationUri).body(savedBook);
    }

//    @DeleteMapping("/book/{bookId}")
//    public String deleteBook(@PathVariable Long bookId){
//        bookService.delete(bookId);
//        return "Book with id " + bookId + " was deleted!";
//    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Long> deleteBook(@PathVariable Long bookId) {

        var deleted = bookService.delete(bookId);

        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookId, HttpStatus.OK);
    }

    @GetMapping("/book/{bookId}")
    public Book getBook(@PathVariable Long bookId) {
        Book book = bookService.findById(bookId);
        Link selfLink = linkTo(methodOn(BookController.class).getBook(book.getId())).withSelfRel();
        book.add(selfLink);
        return bookService.findById(bookId);
    }

//    @GetMapping("/book/title/{title}")
//    Book findByTitle(@PathVariable String title) {
//        Book book = bookService.findByTitle(title);
//        Link selfLink = linkTo(methodOn(BookController.class).getBook(book.getId())).withSelfRel();
//        book.add(selfLink);
//        return book;
//    }

    @GetMapping("/book/title/{title}")
    @HystrixCommand(fallbackMethod = "fallbackfindByTitle")
    Book findByTitleDiscount(@PathVariable String title) {
        Book book = bookService.findByTitle(title);
        Discount discount = discountServiceProxy.findDiscount();
//        book.setPrice(book.getPrice() * (1 - discount.getStudent()));
        log.info(Integer.toString(discount.getInstanceId()));
        book.setPrice(book.getPrice() - (book.getPrice() * discount.getStudent())/100);
        return book;
    }

    Book fallbackfindByTitle(@PathVariable String title) {
        Book book = bookService.findByTitle(title);
        Link selfLink = linkTo(methodOn(BookController.class).getBook(book.getId())).withSelfRel();
        book.add(selfLink);

        return book;
    }

    @GetMapping("/book/author/{author}")
    public List<Book> findByAuthor(@PathVariable String author) {
        return bookService.findByAuthor(author);
    }




//    @GetMapping(value = "/book/list", produces = {"application/hal+json" })
//    public CollectionModel<Book> findBook() {
//        List<Book> books = bookService.getAllBooks();
//        for (final Book book : books) {
//            Link selfLink = linkTo(methodOn(BookController.class).getBook(book.getId())).withSelfRel();
//            book.add(selfLink);
//            Link deleteLink = linkTo(methodOn(BookController.class).deleteBook(book.getId())).withRel("deleteBook");
//            book.add(deleteLink);
//        }
//        Link link = linkTo(methodOn(BookController.class).findBook()).withSelfRel();
//        CollectionModel<Book> result = CollectionModel.of(books, link);
//        return result;
//    }

}

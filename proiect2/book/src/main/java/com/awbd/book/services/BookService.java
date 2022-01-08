package com.awbd.book.services;

import com.awbd.book.model.Book;

import java.util.List;

public interface BookService {
    Book findByTitle(String title);
    List<Book> findByAuthor(String author);
    Book findByPublisherAndTitle(String publisher, String title);
    Book findById(Long id);
    Book save(Book book);
    List<Book> getAllBooks();
    boolean delete(Long id);
}

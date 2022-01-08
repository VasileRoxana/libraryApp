package com.awbd.book.services;

import com.awbd.book.model.Book;
import com.awbd.book.repositories.BookRepository;
import com.awbd.book.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;


    @Override
    public Book findByTitle(String title) {
        Book book = bookRepository.findByTitle(title);
        return book;

    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> books = new LinkedList<>();
        bookRepository.findByAuthor(author).iterator().forEachRemaining(books::add);
        return books;
    }

    @Override
    public Book findByPublisherAndTitle(String publisher, String title) {
        Book book = bookRepository.findByPublisherAndTitle(publisher, title);
        return book;

    }

    @Override
    public Book findById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(! bookOptional.isPresent()) {
            throw new BookNotFound("Book with id " + id + " not found!");
        }
        return bookOptional.get();
    }

    @Override
    public Book save(Book book) {
        Book bookSave = bookRepository.save(book);
        return bookSave;

    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<>();
        bookRepository.findAll().iterator().forEachRemaining(books::add);
        return books;

    }

    @Override
    public boolean delete(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (! bookOptional.isPresent()) {
//            throw new BookNotFound("Book with id " + id + "not found!");
            return false;
        }
        bookRepository.delete(bookOptional.get());
        return true;
    }
}

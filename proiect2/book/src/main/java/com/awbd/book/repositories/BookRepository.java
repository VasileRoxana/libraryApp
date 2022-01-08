package com.awbd.book.repositories;

import com.awbd.book.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByTitle(String title);
    List<Book> findByAuthor(String author);
    Book findByPublisherAndTitle(String publisher, String title);

}

package com.awbd.book.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ApiModel("Book details")
public class Book  extends RepresentationModel<Book> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="title")
//    @Size(max=20, message = "max 20 ch.")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="publisher")
    private String publisher;

    @Column(name="publishingyear")
    private int publishingYear;

    @Column(name="price")
    private double price;

}

package com.reading.getirreading.book.domain.model;

import lombok.Data;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.io.Serializable;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Book_Generator")
    @SequenceGenerator(name = "Book_Generator", sequenceName = "Book_Seq")
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "author",nullable = false)
    private String author;

    @Column(name = "price",nullable = false)
    private Integer price;

    @Column(name = "stock",nullable = false)
    private Integer stock;


    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime updateDateTime;
}
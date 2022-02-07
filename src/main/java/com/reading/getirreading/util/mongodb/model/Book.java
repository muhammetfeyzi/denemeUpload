package com.reading.getirreading.util.mongodb.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.*;
import java.io.Serializable;

import java.time.LocalDateTime;

@Data
@Document(collection = "books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book implements Serializable {

    @Id
    private Long id;
    private String name;
    private String author;
    private Integer price;
    private Integer stock;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
}
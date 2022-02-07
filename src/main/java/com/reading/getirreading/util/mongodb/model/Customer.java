package com.reading.getirreading.util.mongodb.model;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@DynamicInsert
@DynamicUpdate
@Document(collection = "customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    private Long id;
    private String name;
    private String surname;
    @Indexed(unique = true)
    private String email;
    private String phone;
    private String address;
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private  LocalDateTime updateDateTime;
}
package com.reading.getirreading.order.domain.model;


import com.reading.getirreading.book.domain.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reading.getirreading.customer.domain.model.Customer;
import lombok.*;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private Date createDateTime;

    @UpdateTimestamp
     private LocalDateTime updateDateTime;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    Customer customerId;
    Double totalPrice;
    OrderStatus orderStatus;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Book> books = new ArrayList<>();



}
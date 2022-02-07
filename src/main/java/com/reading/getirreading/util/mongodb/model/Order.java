package com.reading.getirreading.util.mongodb.model;



import com.reading.getirreading.book.domain.model.Book;
import com.reading.getirreading.customer.domain.model.Customer;
import com.reading.getirreading.order.domain.model.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "orderss")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    private Long id;
    @CreationTimestamp
    private Date createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    Customer customerId;
    Double totalPrice;
    OrderStatus orderStatus;
    private List<Book> books = new ArrayList<>();
}
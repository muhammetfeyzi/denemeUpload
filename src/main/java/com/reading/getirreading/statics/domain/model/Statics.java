package com.reading.getirreading.statics.domain.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "statics")
public class Statics {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Static_Generator")
    @SequenceGenerator(name = "Static_Generator", sequenceName = "Static_Seq")
    private Long id;

    @Column(name="order_count")
    private Integer orderCount;
    @Column(name="book_count")
    private Integer bookCount;
    @Column(name="purchased_amount")
    private Double purchasedAmount;
    @Column(name="order_id")
    private Long orderId;
    @Column(name="book_id")
    private Long bookId;

}

package com.reading.getirreading.order.domain.model;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderBook {

    private Long bookId;
    private Integer stock;
}
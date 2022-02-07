package com.reading.getirreading.order.domain.model.request;

import com.reading.getirreading.order.domain.model.OrderBook;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    private List<OrderBook> books;
    private Long customerId;

}
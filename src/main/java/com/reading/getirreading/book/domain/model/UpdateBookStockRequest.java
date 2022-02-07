package com.reading.getirreading.book.domain.model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookStockRequest {
    private Integer stock;
    private Long id;

}
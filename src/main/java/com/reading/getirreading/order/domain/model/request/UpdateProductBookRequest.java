package com.reading.getirreading.order.domain.model.request;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductBookRequest {

    private Integer stock;
    private Long id;

}
package com.reading.getirreading.book.domain.model.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BookDto implements Serializable {

    @Size(min = 2,max = 50, message = "Name character must between 2 and 50 characters")
    @NotNull(message = "Enter Name")
    private String name;

    @Size(min = 2,max = 100, message = "Author name must between 2 and 50 chracters.")
    @NotNull(message = "Enter author names")
    private String author;

    @DecimalMin("0.0")
    @NotNull
    private BigDecimal price;

    @Min(0)
    @NotNull
    private Long stock;
}
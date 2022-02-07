package com.reading.getirreading.utils;

import com.reading.getirreading.book.domain.model.Book;
import com.reading.getirreading.customer.domain.model.Customer;
import com.reading.getirreading.order.domain.model.Order;
import com.reading.getirreading.order.domain.model.OrderBook;
import com.reading.getirreading.order.domain.model.request.CreateOrderRequest;

import java.util.Arrays;
import java.util.UUID;

//This class create dummy object for testing our projcet
public class Utils {

    public static final int STOCK = 10;
    public static final double PRICE = 10.0;
    public static final double TOTAL_PRICE = 200.0;

    public static String generateRandomString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    // Creating Customer object
    public static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setAddress(generateRandomString());
        customer.setEmail(generateRandomString());
        customer.setName(generateRandomString());
        return customer;
    }

    //Creating Book object
    public static Book createBook() {
        Book book = new Book();
        book.setStock(STOCK);
        book.setName(generateRandomString());
        book.setPrice((int) PRICE);
        return book;
    }

    // Creating Order object
    public static Order createOrder() {
        Order orderDetail = new Order();
        orderDetail.setBooks(Arrays.asList(createBook()));
        orderDetail.setCustomerId(createCustomer());
        orderDetail.setTotalPrice(TOTAL_PRICE);
        return orderDetail;
    }

    // Creating order request object
    public static CreateOrderRequest createOrderRequest() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setCustomerId(createCustomer().getId());
        OrderBook orderProducts = new OrderBook();
        orderProducts.setBookId(createBook().getId());
        orderProducts.setStock(STOCK);
        createOrderRequest.setBooks(Arrays.asList(orderProducts));
        return createOrderRequest;
    }

}
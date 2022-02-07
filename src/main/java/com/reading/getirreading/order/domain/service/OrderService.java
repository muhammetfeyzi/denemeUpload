package com.reading.getirreading.order.domain.service;


import com.reading.getirreading.book.domain.model.Book;
import com.reading.getirreading.book.domain.model.UpdateBookStockRequest;
import com.reading.getirreading.book.domain.service.BookService;
import com.reading.getirreading.customer.domain.model.Customer;
import com.reading.getirreading.customer.domain.service.CustomerService;
import com.reading.getirreading.exception.ErrorEnum;
import com.reading.getirreading.exception.ReadingApiException;
import com.reading.getirreading.order.domain.model.OrderStatus;
import com.reading.getirreading.order.domain.model.request.CreateOrderRequest;
import com.reading.getirreading.order.domain.model.Order;
import com.reading.getirreading.order.domain.model.OrderBook;
import com.reading.getirreading.order.domain.repository.OrderRepository;
import com.reading.getirreading.util.OrderFunction.UpdateStock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final CustomerService customerService;
    private final UpdateStock updateStock;


    public OrderService(OrderRepository orderRepository,BookService bookService, CustomerService customerService,UpdateStock updateStock){
        this.orderRepository=orderRepository;
        this.bookService=bookService;
        this.customerService=customerService;
        this.updateStock=updateStock;
    }

    @Transactional
    public Order createNewOrder(CreateOrderRequest request) {
        log.info("Order service created new Order  {}", kv("order", request.getBooks()));
        Double totalPrice = 0.0;
        List<Book> bookList = new ArrayList<>();
        Order detail = new Order();
        Customer customer = customerService.getCustomerById(request.getCustomerId());
        if (!CollectionUtils.isEmpty(request.getBooks())) {
            for (OrderBook books : request.getBooks()) {
                Book dbBook = bookService.getBookById(books.getBookId());
                totalPrice += calculatePrice(books, dbBook.getPrice());
                bookList.add(dbBook);
                updateStockRecords(books);
                dbBook.setStock(books.getStock());
            }
            detail.setCustomerId(customer);
            detail.setTotalPrice(totalPrice);
            detail.setBooks(bookList);
            detail.setOrderStatus(OrderStatus.PREPARING);
            orderRepository.save(detail);

        } else {
            throw new ReadingApiException(ErrorEnum.BOOK_NOT_EXIST);
        }
        return detail;
    }

    @Transactional
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
        log.info("Order service delete orderId {}", kv("order", id));

    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();

    }
    public Order getOrder(Long id){
        Optional<Order> order = orderRepository.getOrderById(id);
        if (order != null && order.isPresent()) {
            return order.get();
        } else {
            throw new ReadingApiException(ErrorEnum.ORDER_NOT_EXIST);
        }
    }

    private Integer calculatePrice(OrderBook book, Integer price) {
        return book.getStock() * price;
    }

    public void updateStockRecords(OrderBook orderBook) {
        updateStock.UpdateStockCall(orderBook);
    }


    public List<Order>  getCustomerOrder(Long customerId) {
        Optional<List<Order>> orderDetail = orderRepository.findByCustomerId(customerId);
        if (orderDetail != null && orderDetail.isPresent()) {
            return orderDetail.get();
        } else {
            throw new ReadingApiException(ErrorEnum.ORDER_NOT_EXIST);
        }
    }
    public List<Order> findOrderBetweenCreatedDate(Date start, Date end){
        return orderRepository.findOrderBetweenCreatedDate(start,end);
    }
    public Page<Order> getOrdersByCustomerId(Long id){
        return orderRepository.getOrdersByCustomerId(id);
    }
}
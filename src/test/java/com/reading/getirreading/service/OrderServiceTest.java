package com.reading.getirreading.service;

import com.reading.getirreading.book.domain.model.Book;
import com.reading.getirreading.book.domain.service.BookService;
import com.reading.getirreading.customer.domain.model.Customer;
import com.reading.getirreading.customer.domain.service.CustomerService;
import com.reading.getirreading.exception.ReadingApiException;
import com.reading.getirreading.order.domain.model.Order;
import com.reading.getirreading.order.domain.model.request.CreateOrderRequest;
import com.reading.getirreading.order.domain.repository.OrderRepository;
import com.reading.getirreading.order.domain.service.OrderService;
import com.reading.getirreading.util.OrderFunction.UpdateStock;
import com.reading.getirreading.utils.Utils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.when;

// i write Mockito for testing our system
// you can leanr more about information about mockito Junit for testing spring boot
//https://www.springboottutorial.com/spring-boot-unit-testing-and-mocking-with-mockito-and-junit
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    CustomerService customerService;

    @Mock
    BookService bookService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    // testing multi thread stock change with pesimistic lock spring jpa Data
    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("Muhammet Feyzi");
    EntityManager em1 = emf.createEntityManager(); // 1. Entity Yönetici
    EntityManager em2 = emf.createEntityManager(); // 2. Entity Yönetici
    EntityTransaction trx1 = em1.getTransaction(); // Transaction 1
    EntityTransaction trx2 = em2.getTransaction(); // Transaction 2

    //Create new order Success case
    @Test
    public void updateOrderStock_shouldBeSuccess() throws InterruptedException {

        Book book = Utils.createBook();
        book.setId(book.getId());
        book.setStock(20);
        book.setAuthor("Muhammet Feyzi Saglam");
        trx1.begin();
        em1.persist(book);
        trx1.commit();

        ExecutorService ex = Executors.newFixedThreadPool(2);

        UpdateStock updateStock1 = new UpdateStock(emf, em1, trx1, 5); // 1. mission update bok stock
        UpdateStock updateStock2 = new UpdateStock(emf, em2, trx2, 10); // 2. misson update book stock

        List<Callable> updateStocks = new ArrayList<Callable>();
        updateStocks.add(updateStock1);
        updateStocks.add(updateStock2);
        ex.shutdown();
        ex.awaitTermination(5, TimeUnit.MINUTES);
        em1.refresh(em1.find(Book.class, book.getId()));//update stock
        System.out.println("Stock : "+book.getStock());
        em1.close();
        em2.close();
        emf.close();
        emf.close();
        when(bookService.getBookById(book.getId())).thenReturn(book);
        Order result = orderService.createNewOrder(Utils.createOrderRequest());
        Assert.assertNotNull(result);
    }

    //Create new order Success case
    @Test
    public void createNewOrder_shouldBeSuccess() {
        Customer customer = Utils.createCustomer();
        Book book = Utils.createBook();
        when(customerService.getCustomerById(customer.getId())).thenReturn(customer);
        when(bookService.getBookById(book.getId())).thenReturn(book);
        Order result = orderService.createNewOrder(Utils.createOrderRequest());
        Assert.assertNotNull(result);
    }

    //Create new Order Failure Case
    @Test
    public void createNewOrder_shouldBeError_productEmpty() {
        expectedException.expect(ReadingApiException.class);
        Customer customer = Utils.createCustomer();
        when(customerService.getCustomerById(customer.getId())).thenReturn(customer);
        CreateOrderRequest request = Utils.createOrderRequest();
        request.setBooks(null);
        orderService.createNewOrder(request);

    }


    // Fetching Customer Order Success Case
    @Test
    public void getCustomerOrder_shouldBeSuccess() {
        Customer customer = Utils.createCustomer();
        when(orderRepository.findByCustomerId(customer.getId())).thenReturn(Optional.<List<Order>>of((List<Order>) Utils.createOrder()));
        List<Order> result = orderService.getCustomerOrder(customer.getId());
        Assert.assertNotNull(result);
    }

    // Fetch Customer Order Error Failure
    @Test
    public void getCustomerOrder_shouldBeError_orderNotFound() {
        expectedException.expect(ReadingApiException.class);
        Customer customer = Utils.createCustomer();
        when(orderRepository.findByCustomerId(customer.getId())).thenReturn(null);
        orderService.getCustomerOrder(customer.getId());
    }

}

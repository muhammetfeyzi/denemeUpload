package com.reading.getirreading.order.api.v1;



import com.reading.getirreading.customer.domain.model.Customer;
import com.reading.getirreading.order.domain.model.Order;
import com.reading.getirreading.order.domain.model.OrderBook;
import com.reading.getirreading.order.domain.model.request.CreateOrderRequest;
import com.reading.getirreading.order.domain.model.request.UpdateProductBookRequest;
import com.reading.getirreading.order.domain.service.OrderService;
import io.swagger.models.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping("/newOrder")
    public ResponseEntity<List<Order>> createNewOrder(@Valid @RequestBody CreateOrderRequest request) {
        return new ResponseEntity(orderService.createNewOrder(request), HttpStatus.OK);
    }

    @GetMapping("/customerOrders")
    public ResponseEntity<List<Order>> getCustomerOrders(@RequestParam Long customerId) {
        return ResponseEntity.ok(orderService.getCustomerOrder(customerId));
    }

    @GetMapping("{orederId}")
    public ResponseEntity<Order> getOrderById(@RequestParam Long orderId){
        return  ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @GetMapping("/createdate")
    public ResponseEntity<List<Order>> findOrderBetweenCreatedDate(@RequestParam Date start, @RequestParam Date end){
        return ResponseEntity.ok(orderService.findOrderBetweenCreatedDate(start,end));
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<Page<Order>> getOrdersByCustomerId(@RequestParam Long customerId){
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }
    @PutMapping("/updateStock")
    public void updateOrderBookStock(@RequestBody @Valid OrderBook orderBook){
        orderService.updateStockRecords(orderBook);
    }
    @DeleteMapping("/{customerOrderId}")
    public void deleteCustomerOrder(@RequestParam Long id){
        orderService.deleteOrder(id);
    }

}
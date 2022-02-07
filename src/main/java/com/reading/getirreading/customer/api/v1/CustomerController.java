package com.reading.getirreading.customer.api.v1;



import com.reading.getirreading.customer.domain.model.Customer;
import com.reading.getirreading.customer.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/*
    Author Muhammet Feyzi Saglam
    6.02.2022
    this is endpoint fo Customer request on any client request
 */


@RestController
@RequestMapping(path = "/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    // We used the valid spring boot animation to control the request object coming to the end point
    //we will use post mapping http function because of this is insertion in my database.
    // this function provided getir assestment create new Customer requirements
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer) {
        return new ResponseEntity(customerService.createCustomer(customer), HttpStatus.OK);
    }

    //just one customer fetching this end point retrieve in my database
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    //we found all customer in this function and we will use Page response ResponseEntity<PAge<Customer>>
    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomer(@RequestParam int pageIndex, @RequestParam int pageSize) {
        return ResponseEntity.ok(customerService.getAllCustomer(pageIndex, pageSize));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@RequestParam Long id){
         customerService.deleteCustomerById(id);
    }

}
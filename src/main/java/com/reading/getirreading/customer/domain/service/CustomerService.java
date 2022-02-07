package com.reading.getirreading.customer.domain.service;


import com.reading.getirreading.customer.domain.model.Customer;
import com.reading.getirreading.customer.domain.repository.CustomerRepository;
import com.reading.getirreading.exception.ErrorEnum;
import com.reading.getirreading.exception.ReadingApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
public class CustomerService {

    // i use constructor injection in spring boot project because of that is best practice in dependency injection
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository =customerRepository;
    }

    // this is create new customer issues function
    @Transactional
    public Customer createCustomer(Customer customer) {
        if(customer == null){
            throw new ReadingApiException(ErrorEnum.CONTENT_NOT_FOUND_ERROR);
        }
        log.info("Customer service create new  Customer {}", kv("Customer", customer.toString()));
        return customerRepository.save(customer);
    }

    //
    public Customer getCustomerById(Long id) {
        //java optional feature. we use java to handle null point exceptions error
        Optional<Customer> dbCustomerEntity = customerRepository.findById(id);
        if (dbCustomerEntity.isPresent()) {
            log.info("Customer service fetch customer {}", kv("Customer", dbCustomerEntity.get().getName()));
            return dbCustomerEntity.get();
        } else {
            throw new ReadingApiException(ErrorEnum.CUSTOMER_NOT_EXIST);
        }
    }

    public Page<Customer> getAllCustomer(int pageIndex, int pageSize) {
        Page<Customer> response = customerRepository.findAll(PageRequest.of(pageIndex, pageSize));
        log.info("Customer Service Fetch all Customer {} {}", kv("pageIndex", pageIndex), kv("pageSize", pageSize));
        return response;
    }

    public void deleteCustomerById(Long id){
        log.info("Customer service delete customer{}", kv("Customer", id));
        customerRepository.deleteById(id);
    }
}
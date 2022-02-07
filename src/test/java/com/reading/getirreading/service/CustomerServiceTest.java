package com.reading.getirreading.service;


import com.reading.getirreading.customer.domain.model.Customer;
import com.reading.getirreading.customer.domain.repository.CustomerRepository;
import com.reading.getirreading.customer.domain.service.CustomerService;
import com.reading.getirreading.exception.ReadingApiException;
import com.reading.getirreading.utils.Utils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    // Create  Customer Succes Case
    @Test
    public void createCustomer_shouldBeSuccess() {
        Customer customer = Utils.createCustomer();
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        Customer result = customerService.createCustomer(Utils.createCustomer());
        Assert.assertNotNull(result);
    }

    //Create Customer Failure case
    @Test
    public void createCustomer_shouldBeError_customerIsNull() {
        expectedException.expect(ReadingApiException.class);
        customerService.createCustomer(null);
    }

    // Fetch Customer By customer id success case
    @Test
    public void getCustomerById_shouldBeSuccess() {
        Customer customer = Utils.createCustomer();
        when(customerRepository.findById(customer.getId())).thenReturn(java.util.Optional.of(customer));
        Customer result = customerService.getCustomerById(Utils.createCustomer().getId());
        Assert.assertNotNull(result);
    }


    // fetch customer by customer id failure case
    @Test
    public void getCustomerById_shouldBeError_customerNotFound() {
        Customer customer = Utils.createCustomer();
        expectedException.expect(ReadingApiException.class);
        customerService.getCustomerById(customer.getId());
    }

}

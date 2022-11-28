package com.pythonarabia.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // this will save and flush the customer
        customerRepository.saveAndFlush(customer);
        // Rest template is web client will call fraud and pass the customer ID to the URl
        // and respond whether it is fraud or not
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://127.0.0.1:8081/api/v1/fraud-check/{customerId}", FraudCheckResponse.class,
                customer.getId());
        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
    }
}

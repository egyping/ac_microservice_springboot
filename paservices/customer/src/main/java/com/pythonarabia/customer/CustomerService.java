package com.pythonarabia.customer;

import com.pythonarabia.fraud.FraudCheckResponse;
import com.pythonarabia.fraud.FraudClient;
import com.pythonarabia.notifications.NotificationsClient;
import com.pythonarabia.notifications.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    // adding the fraud web client
    private final FraudClient fraudClient;

    // Notifications client
    private final NotificationsClient notificationsClient;

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
        //FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
        // the url hard coded in case of rest template
        //"http://127.0.0.1:8081/api/v1/fraud-check/{customerId}",
        // in case of eureka just mention the service name
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId());
//        if (fraudCheckResponse.isFraudster()){
//            throw new IllegalStateException("fraudster");
//        }


        // another option to use th open feign client
        // FraudCheckResponse the one at the web client not in customer
        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        // send notification
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Amigoscode...",
                        customer.getFirstName())
        );

        notificationsClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hey! .. ", customer.getFirstName())
                )
        );


    }
}

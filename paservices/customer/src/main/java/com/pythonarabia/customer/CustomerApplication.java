package com.pythonarabia.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.pythonarabia.amqp",
                "com.pythonarabia.customer",
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = {"com.pythonarabia.fraud",
                "com.pythonarabia.notifications",
                })

public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}

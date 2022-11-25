package com.pythonarabia.fraud;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckService {


    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;



    public boolean isFraudulentCustomer(Integer customerId){
        // store the query if the customer fraud or not
        // for demo purpose all customers are not fraudester
        fraudCheckHistoryRepository.save(
          FraudCheckHistory.builder()
                  .isFraudster(false)
                  .customerId(customerId)
                  .createdAt(LocalDateTime.now())
                  .build()
        );
    return false;
    }
}

package com.scaler.payment.paymentService.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaymentConfig {


    @Bean
    public RestTemplate restTemplateBean() {
        return new RestTemplate();
    }
}

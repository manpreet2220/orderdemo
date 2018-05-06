package com.example.orderplacement;

import com.example.orderplacement.service.OrderPlacementService;
import com.example.orderplacement.serviceImpl.OrderPlacementServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderPlacementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderPlacementApplication.class, args);
    }

    @Bean
    public OrderPlacementService servcieVibrationData() {
        return new OrderPlacementServiceImpl();
    }
}

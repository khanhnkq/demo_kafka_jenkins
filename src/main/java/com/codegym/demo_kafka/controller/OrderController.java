package com.codegym.demo_kafka.controller;

import com.codegym.demo_kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private KafkaProducerService producer;

    @PostMapping
    public String createOrder(@RequestBody String order) {
        producer.sendMessage(order);
        return "Order sent to Kafka!";
    }

    
}

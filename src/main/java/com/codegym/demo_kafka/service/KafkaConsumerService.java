package com.codegym.demo_kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "order-topic", groupId = "group-1")
    public void consume(String message) {
        System.out.println("Received: " + message);
    }
}
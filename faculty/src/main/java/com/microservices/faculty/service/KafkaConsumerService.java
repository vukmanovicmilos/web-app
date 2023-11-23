package com.microservices.faculty.service;

import com.microservices.report.dto.CourseSummaryDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Message<CourseSummaryDto> message) {
        System.out.println("Received message: " + message.getPayload());
    }
}

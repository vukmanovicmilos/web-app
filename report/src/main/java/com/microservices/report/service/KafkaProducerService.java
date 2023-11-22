package com.microservices.report.service;


import com.microservices.report.dto.CourseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, CourseDto> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, CourseDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(CourseDto course) {
        Message<CourseDto> message = MessageBuilder
                .withPayload(course)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        kafkaTemplate.send(message);
    }
}

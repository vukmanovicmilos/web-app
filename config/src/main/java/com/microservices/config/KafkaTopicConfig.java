package com.microservices.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put("bootstrap.servers", "kafka1:9092,kafka2:9093,kafka3:9094");
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic javaguidesTopic() {
        return TopicBuilder
                .name(topicName)
                .partitions(3)
                .replicas(2)
                .build();
    }
}
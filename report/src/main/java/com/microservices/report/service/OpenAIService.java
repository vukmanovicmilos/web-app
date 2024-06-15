package com.microservices.report.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAIService {

    private final ChatClient chatClient;

    OpenAIService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getSummary(Map<String, Object> params) {
        System.out.println("BBBBBBBB" + chatClient);
        String prompt = String.format("""
                Create short funny description for course name %s
                and course start date %s
                and teacher title %s
                and teacher last name %s
                """, params.get("courseName"), params.get("courseDate"), params.get("teacherTitle"), params.get("teacherLastName"));
        return this.chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}

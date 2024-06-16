package com.microservices.openAI.service;

import com.microservices.openAI.dto.CourseSummaryRequestDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

    private final ChatClient chatClient;

    OpenAIService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    public String getSummary(CourseSummaryRequestDto courseSummaryRequest) {
        String prompt = String.format("""
                        Create short funny description for course name %s
                        and course start date %s
                        and teacher title %s
                        and teacher last name %s
                        """,
                courseSummaryRequest.getCourseName(),
                courseSummaryRequest.getCourseDate(),
                courseSummaryRequest.getTeacherTitle(),
                courseSummaryRequest.getTeacherLastName());
        return this.chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}

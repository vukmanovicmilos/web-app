package com.microservices.openAI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.openAI.dto.CourseSummaryRequestDto;
import com.microservices.openAI.service.OpenAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Open AI")
@RestController
@RequiredArgsConstructor
@RequestMapping("open-ai")
public class OpenAIRestController {

    private final OpenAIService openAIService;

    @Operation(summary = "Returns summary for report generated with openAI")
    @PostMapping("summary")
    public String getSummary(@RequestBody CourseSummaryRequestDto courseSummaryRequest) {
        return openAIService.getSummary(courseSummaryRequest);
    }

    @Operation(summary = "Get all students and save it to the vector database")
    @GetMapping("sync-students")
    public void syncStudents() throws JsonProcessingException {
        openAIService.syncStudents();
    }

    @Operation(summary = "Get response from openAI for question")
    @GetMapping("question")
    public String askQuestion(@RequestParam(value = "question", defaultValue = "How many students have sport hobbies?") String question) {
        return openAIService.askQuestion(question);
    }
}

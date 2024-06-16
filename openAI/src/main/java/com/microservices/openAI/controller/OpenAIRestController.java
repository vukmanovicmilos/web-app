package com.microservices.openAI.controller;

import com.microservices.openAI.dto.CourseSummaryRequestDto;
import com.microservices.openAI.service.OpenAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

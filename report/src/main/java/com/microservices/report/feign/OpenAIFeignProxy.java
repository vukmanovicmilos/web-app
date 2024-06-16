package com.microservices.report.feign;

import com.microservices.report.dto.CourseSummaryRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "open-ai")
public interface OpenAIFeignProxy {

    @Operation(summary = "Returns summary for report generated with openAI (from open-ai ms)")
    @PostMapping("open-ai/summary")
    String getSummary(@RequestBody CourseSummaryRequestDto courseSummaryRequest);

}

package com.microservices.openAI.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(name = "faculty")
public interface FacultyFeignProxy {

    @GetMapping("students/all")
    List<Object> getStudents();

}

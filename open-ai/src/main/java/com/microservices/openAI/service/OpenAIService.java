package com.microservices.openAI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.openAI.dto.CourseSummaryRequestDto;
import com.microservices.openAI.feign.FacultyFeignProxy;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenAIService {

    private final ChatClient chatClient;
    private final FacultyFeignProxy facultyFeignProxy;
    private final VectorStore vectorStore;
    @Value("classpath:/prompts/spring-boot-reference.st")
    private Resource studentPromptTemplate;

    OpenAIService(ChatClient.Builder chatClientBuilder, FacultyFeignProxy facultyFeignProxy, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.facultyFeignProxy = facultyFeignProxy;
        this.vectorStore = vectorStore;
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

    public void syncStudents() throws JsonProcessingException {
        List<Object> students = facultyFeignProxy.getStudents();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(students);
        List<Document> documents = List.of(new Document(jsonString));
        TokenTextSplitter textSplitter = new TokenTextSplitter();
        vectorStore.add(textSplitter.apply(documents));
    }

    public String askQuestion(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(studentPromptTemplate);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("input", question);
        promptParameters.put("documents", String.join("\n", findSimilarDocuments(question)));
        Prompt temp = promptTemplate.create(promptParameters);
        return this.chatClient.prompt()
                .user(temp.getContents())
                .call()
                .content();
    }

    private List<String> findSimilarDocuments(String question) {
        List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.query(question).withTopK(3));
        return similarDocuments.stream().map(Document::getContent).toList();
    }
}

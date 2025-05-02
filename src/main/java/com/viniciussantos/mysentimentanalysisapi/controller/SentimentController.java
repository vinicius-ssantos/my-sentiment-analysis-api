package com.viniciussantos.mysentimentanalysisapi.controller;


import com.viniciussantos.mysentimentanalysisapi.model.CommentRequest;
import com.viniciussantos.mysentimentanalysisapi.model.SentimentResponse;
import com.viniciussantos.mysentimentanalysisapi.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/sentiment")
public class SentimentController {

    @Autowired
    private SentimentAnalysisService service;

    @PostMapping
    public Mono<SentimentResponse> analyze(@RequestBody CommentRequest request) {
        return service.analyzeText(request.getComment());
    }
}

package com.viniciussantos.mysentimentanalysisapi.service;


import com.viniciussantos.mysentimentanalysisapi.client.HuggingFaceClient;
import com.viniciussantos.mysentimentanalysisapi.model.SentimentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SentimentAnalysisService {

    @Autowired
    private HuggingFaceClient huggingFaceClient;

    public Mono<SentimentResponse> analyzeText(String comment) {
        return huggingFaceClient.analyzeSentiment(comment)
                .map(result -> new SentimentResponse(result.sentiment(), result.score(), result.dominantLabel()));
    }
}

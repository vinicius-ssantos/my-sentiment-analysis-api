package com.viniciussantos.mysentimentanalysisapi.service;


import com.viniciussantos.mysentimentanalysisapi.client.HuggingFaceClient;
import com.viniciussantos.mysentimentanalysisapi.model.SentimentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisService {

    @Autowired
    private HuggingFaceClient huggingFaceClient;

    public SentimentResponse analyzeText(String comment) {
        String sentiment = huggingFaceClient.analyzeSentiment(comment).block();
        return new SentimentResponse(sentiment, 1.0);
    }
}
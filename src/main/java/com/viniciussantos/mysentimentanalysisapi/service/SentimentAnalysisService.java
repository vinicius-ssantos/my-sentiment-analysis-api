package com.viniciussantos.mysentimentanalysisapi.service;


import com.viniciussantos.mysentimentanalysisapi.client.HuggingFaceClient;
import com.viniciussantos.mysentimentanalysisapi.model.SentimentResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Service responsible for analyzing text sentiment using the Hugging Face API.
 */
@Service
public class SentimentAnalysisService {

    private final HuggingFaceClient huggingFaceClient;

    /**
     * Creates a new SentimentAnalysisService with the required dependencies.
     * 
     * @param huggingFaceClient the client used to communicate with Hugging Face API
     */
    public SentimentAnalysisService(HuggingFaceClient huggingFaceClient) {
        this.huggingFaceClient = huggingFaceClient;
    }

    public Mono<SentimentResponse> analyzeText(String comment) {
        return huggingFaceClient.analyzeSentiment(comment)
                .map(result -> new SentimentResponse(result.sentiment(), result.score(), result.dominantLabel()));
    }
}

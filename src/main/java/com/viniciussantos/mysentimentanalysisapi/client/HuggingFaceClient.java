package com.viniciussantos.mysentimentanalysisapi.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;
import java.util.List;
@Component
public class HuggingFaceClient {

    @Value("${huggingface.api.token}")
    private String apiToken;

    private final WebClient webClient = WebClient.create("https://api-inference.huggingface.co");

    public Mono<SentimentResult> analyzeSentiment(String text) {
        String model = "nlptown/bert-base-multilingual-uncased-sentiment";

        return webClient.post()
                .uri("/models/" + model)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiToken)
                .bodyValue(Map.of("inputs", text))
                .retrieve()
                .bodyToMono(List.class)
                .map(this::parseSentimentResult);
    }

    private SentimentResult parseSentimentResult(List<?> response) {
        if (response == null || response.isEmpty()) return new SentimentResult("Desconhecido", 0.0, "n/a");

        List<?> scores = (List<?>) response.get(0);
        String dominantLabel = "";
        double maxScore = -1;

        double score5 = 0, score4 = 0, score3 = 0, score2 = 0, score1 = 0;
        for (Object obj : scores) {
            Map<String, Object> map = (Map<String, Object>) obj;
            String label = (String) map.get("label");
            double score = ((Number) map.get("score")).doubleValue();

            if (score > maxScore) {
                maxScore = score;
                dominantLabel = label;
            }

            switch (label) {
                case "5 stars" -> score5 = score;
                case "4 stars" -> score4 = score;
                case "3 stars" -> score3 = score;
                case "2 stars" -> score2 = score;
                case "1 star"  -> score1 = score;
            }
        }

        String sentiment;
        if ((score5 + score4) > 0.6) sentiment = "Positivo";
        else if ((score1 + score2) > 0.5) sentiment = "Negativo";
        else sentiment = "Neutro";

        return new SentimentResult(sentiment, maxScore, dominantLabel);
    }

    public record SentimentResult(String sentiment, double score, String dominantLabel) {}
}
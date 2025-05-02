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

    public Mono<String> analyzeSentiment(String text) {
        String model = "nlptown/bert-base-multilingual-uncased-sentiment";

        return webClient.post()
            .uri("/models/" + model)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiToken)
            .bodyValue(Map.of("inputs", text))
            .retrieve()
            .bodyToMono(List.class)
            .map(this::parseSentiment);
    }

    private String parseSentiment(List<?> response) {
        if (response == null || response.isEmpty()) return "Desconhecido";

        Map<String, Object> first = (Map<String, Object>) ((List<?>) response.get(0)).get(0);
        String label = (String) first.get("label");
        return label;
    }
}
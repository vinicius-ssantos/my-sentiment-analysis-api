package com.viniciussantos.mysentimentanalysisapi.controller;

import com.viniciussantos.mysentimentanalysisapi.client.HuggingFaceClient;
import com.viniciussantos.mysentimentanalysisapi.model.CommentRequest;
import com.viniciussantos.mysentimentanalysisapi.model.SentimentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Controller
public class WebController {

    @Autowired
    private HuggingFaceClient huggingFaceClient;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("commentRequest", new CommentRequest());
        return "index";
    }

    @PostMapping("/analyze")
    public Mono<String> analyzeComment(@ModelAttribute("commentRequest") CommentRequest request, Model model) {
        String comment = request.getComment();
        System.out.println("🔍 Comentário recebido: " + comment);

        if (comment == null || comment.trim().isEmpty()) {
            model.addAttribute("error", "⚠️ O comentário não pode estar vazio.");
            return Mono.just("index");
        }

        return huggingFaceClient.analyzeSentiment(comment)
                .map(result -> {
                    SentimentResponse response = new SentimentResponse(result.sentiment(), result.score(), result.dominantLabel());
                    model.addAttribute("comment", comment);
                    model.addAttribute("result", response);
                    return "index";
                });
    }
}

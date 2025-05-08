package com.viniciussantos.mysentimentanalysisapi.controller;

import com.viniciussantos.mysentimentanalysisapi.model.CommentRequest;
import com.viniciussantos.mysentimentanalysisapi.service.SentimentAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Controller that handles the web interface for sentiment analysis.
 */
@Controller
public class WebController {

    private static final Logger logger = LoggerFactory.getLogger(WebController.class);
    private final SentimentAnalysisService sentimentService;

    /**
     * Creates a new WebController with the required dependencies.
     *
     * @param sentimentService the service used for sentiment analysis
     */
    public WebController(SentimentAnalysisService sentimentService) {
        this.sentimentService = sentimentService;
    }

    /**
     * Displays the home page with an empty form.
     *
     * @param model the Spring MVC model
     * @return the name of the view template
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("commentRequest", new CommentRequest(""));
        return "index";
    }

    /**
     * Analyzes a comment submitted via the web form.
     *
     * @param request the comment request from the form
     * @param model the Spring MVC model
     * @return a Mono containing the name of the view template
     */
    @PostMapping("/analyze")
    public Mono<String> analyzeComment(@ModelAttribute("commentRequest") CommentRequest request, Model model) {
        String comment = request.getComment();
        logger.info("Received comment for analysis: {}", comment);

        if (comment == null || comment.trim().isEmpty()) {
            model.addAttribute("error", "⚠️ O comentário não pode estar vazio.");
            return Mono.just("index");
        }

        return sentimentService.analyzeText(comment)
                .map(response -> {
                    model.addAttribute("comment", comment);
                    model.addAttribute("result", response);
                    return "index";
                })
                .onErrorResume(e -> {
                    logger.error("Error analyzing comment", e);
                    model.addAttribute("error", "⚠️ Ocorreu um erro ao analisar o comentário. Tente novamente mais tarde.");
                    return Mono.just("index");
                });
    }
}

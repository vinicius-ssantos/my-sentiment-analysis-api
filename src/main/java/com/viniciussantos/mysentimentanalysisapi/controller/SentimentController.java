package com.viniciussantos.mysentimentanalysisapi.controller;

import com.viniciussantos.mysentimentanalysisapi.model.CommentRequest;
import com.viniciussantos.mysentimentanalysisapi.model.SentimentResponse;
import com.viniciussantos.mysentimentanalysisapi.service.SentimentAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/**
 * REST controller that provides sentiment analysis API endpoints.
 */
@RestController
@RequestMapping("/api/sentiment")
public class SentimentController {

    private static final Logger logger = LoggerFactory.getLogger(SentimentController.class);
    private final SentimentAnalysisService service;

    /**
     * Creates a new SentimentController with the required dependencies.
     *
     * @param service the service used for sentiment analysis
     */
    public SentimentController(SentimentAnalysisService service) {
        this.service = service;
    }

    /**
     * Analyzes the sentiment of a comment.
     *
     * @param request the comment request containing the text to analyze
     * @return a Mono containing the sentiment analysis response
     */
    @PostMapping
    public Mono<SentimentResponse> analyze(@RequestBody CommentRequest request) {
        if (request == null || request.getComment() == null || request.getComment().trim().isEmpty()) {
            logger.warn("Received empty comment for analysis");
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment cannot be empty"));
        }

        logger.info("Analyzing sentiment for comment");
        return service.analyzeText(request.getComment())
                .doOnSuccess(response -> logger.info("Sentiment analysis completed successfully"))
                .doOnError(e -> logger.error("Error during sentiment analysis", e));
    }
}

package com.viniciussantos.mysentimentanalysisapi.model;

/**
 * SentimentResponse is a model class that represents the response from the sentiment analysis API.
 * It contains the sentiment, score, and dominant label of the analyzed text.
 */

public class SentimentResponse {
    private String sentiment;
    private double score;
    private String dominantLabel;

    public SentimentResponse(String sentiment, double score, String dominantLabel) {
        this.sentiment = sentiment;
        this.score = score;
        this.dominantLabel = dominantLabel;
    }

    public String getSentiment() {
        return sentiment;
    }

    public double getScore() {
        return score;
    }

    public String getDominantLabel() {
        return dominantLabel;
    }
}

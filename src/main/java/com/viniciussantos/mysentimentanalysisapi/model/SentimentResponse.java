package com.example.sentiment.model;

public class SentimentResponse {
    private String sentiment;
    private double score;

    public SentimentResponse(String sentiment, double score) {
        this.sentiment = sentiment;
        this.score = score;
    }

    public String getSentiment() {
        return sentiment;
    }

    public double getScore() {
        return score;
    }
}
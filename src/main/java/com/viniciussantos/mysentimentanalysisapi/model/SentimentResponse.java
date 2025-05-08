package com.viniciussantos.mysentimentanalysisapi.model;

/**
 * Represents the response from the sentiment analysis API.
 * Implemented as a record for immutability and conciseness.
 *
 * @param sentiment The overall sentiment classification (Positive, Negative, Neutral)
 * @param score The confidence score of the sentiment analysis
 * @param dominantLabel The most likely sentiment label from the model
 */
public record SentimentResponse(String sentiment, double score, String dominantLabel) {
    /**
     * Returns the sentiment classification.
     * This method is provided for compatibility with frameworks expecting JavaBean conventions.
     *
     * @return the sentiment classification
     */
    public String getSentiment() {
        return sentiment;
    }

    /**
     * Returns the confidence score.
     * This method is provided for compatibility with frameworks expecting JavaBean conventions.
     *
     * @return the confidence score
     */
    public double getScore() {
        return score;
    }

    /**
     * Returns the dominant label from the model.
     * This method is provided for compatibility with frameworks expecting JavaBean conventions.
     *
     * @return the dominant label
     */
    public String getDominantLabel() {
        return dominantLabel;
    }
}

package com.viniciussantos.mysentimentanalysisapi.model;

/**
 * Represents a request containing a comment to be analyzed.
 * Implemented as a record for immutability and conciseness.
 *
 * @param comment The text comment to analyze
 */
public record CommentRequest(String comment) {
    /**
     * Returns the comment text.
     * This method is provided for compatibility with frameworks expecting JavaBean conventions.
     *
     * @return the comment text
     */
    public String getComment() {
        return comment;
    }
}

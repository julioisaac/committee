package org.cia.committee.domain.model;

import java.time.LocalDateTime;

public class Comment {

    private final String text;

    private final LocalDateTime createdAt;

    public Comment(String text) {
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

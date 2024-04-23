package org.cia.committee.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Problem {

    private UUID uuid;

    private String name;

    private UUID committeeUUID;

    private UUID userUUID;

    private Comment comment;

    private LocalDateTime createdAt;

    private ProblemState state;

    public Problem(UUID userUUID, String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.userUUID = userUUID;
        this.createdAt = LocalDateTime.now();
        this.state = ProblemState.OPENED;
    }

    public void addComment(Comment comment) {
        if (this.state != ProblemState.CLOSED) {
            this.comment = comment;
            this.state = ProblemState.READY;
        }
    }

    public boolean canBeAssigned() {
        return this.committeeUUID == null && this.state == ProblemState.READY;
    }

    public void assign(UUID committeeUUID) {
        this.committeeUUID = committeeUUID;
    }

    public void close() {
        this.state = ProblemState.CLOSED;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public ProblemState getState() {
        return state;
    }

    public UUID getUserUUID() {
        return userUUID;
    }

    public UUID getCommitteeUUID() {
        return committeeUUID;
    }
}

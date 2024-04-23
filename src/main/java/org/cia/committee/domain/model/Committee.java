package org.cia.committee.domain.model;

import java.time.LocalDate;
import java.util.UUID;

public class Committee {

    private UUID uuid;

    private LocalDate date;

    private UUID directorUUID;

    private CommitteeState state;

    public Committee(LocalDate date) {
        this.uuid = UUID.randomUUID();
        this.date = date;
        this.state = CommitteeState.PLANNED;
    }

    public void assignDirector(UUID directorUUID) {
        this.directorUUID = directorUUID;
    }

    public void start() {
        if (this.date.equals(LocalDate.now()) && this.directorUUID != null) {
            this.state = CommitteeState.IN_PROGRESS;
        }
    }

    public void close() {
        this.state = CommitteeState.CLOSED;
    }

    public LocalDate getDate() {
        return date;
    }

    public UUID getUUID() {
        return uuid;
    }

    public CommitteeState getState() {
        return state;
    }
}

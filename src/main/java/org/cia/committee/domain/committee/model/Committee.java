package org.cia.committee.domain.committee.model;

import java.time.LocalDate;
import java.util.UUID;

public class Committee {

    private UUID uuid;

    private LocalDate date;

    private CommitteeState state;

    public Committee(LocalDate date) {
        this.uuid = UUID.randomUUID();
        this.date = date;
        this.state = CommitteeState.PLANNED;
    }

    public void start() {
        if (this.date.equals(LocalDate.now())) {
            this.state = CommitteeState.IN_PROGRESS;
        }
    }

    public void close() {
        this.state = CommitteeState.CLOSED;
    }

    public UUID getUUID() {
        return uuid;
    }

    public CommitteeState getState() {
        return state;
    }
}

package org.cia.committee.domain.events;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class DomainEvent<T> {

    private UUID aggregateId;
    private LocalDateTime timestamp;
    private EventType type;
    private T details;

    public UUID getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(UUID aggregateId) {
        this.aggregateId = aggregateId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public T getDetails() {
        return details;
    }

    public void setDetails(T details) {
        this.details = details;
    }
}

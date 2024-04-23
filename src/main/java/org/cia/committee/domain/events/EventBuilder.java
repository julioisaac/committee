package org.cia.committee.domain.events;

public class EventBuilder<T> {

    private final DomainEvent<T> event;

    public EventBuilder() {
        this.event = new DomainEvent();
    }

    public EventBuilder<T> details(T eventData) {
        this.event.setDetails(eventData);
        return this;
    }

    public EventBuilder<T> type(EventType type) {
       this.event.setType(type);
        return this;
    }
    public DomainEvent<T> build() {
        return event;
    }

    public static <T> EventBuilder<T> create() {
        return new EventBuilder<>();
    }
}

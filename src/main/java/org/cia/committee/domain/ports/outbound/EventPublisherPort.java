package org.cia.committee.domain.ports.outbound;

public interface EventPublisherPort<T>{
    void publisher(T event);
}

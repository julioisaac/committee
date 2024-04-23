package org.cia.committee.domain.ports.outbound;

public interface EventPublisherPort{
    void publisher(Object event);
}

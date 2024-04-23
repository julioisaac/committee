package org.cia.committee.domain.ports.inbound;

import org.cia.committee.common.exception.ResourceNotFoundException;

import java.util.UUID;

public interface CloseProblemUseCase {
    void close(UUID problemUUID) throws ResourceNotFoundException;
}

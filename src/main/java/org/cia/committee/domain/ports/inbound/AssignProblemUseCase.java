package org.cia.committee.domain.ports.inbound;

import org.cia.committee.common.exception.ParameterNotFoundException;
import org.cia.committee.common.exception.ResourceNotFoundException;

import java.util.UUID;

public interface AssignProblemUseCase {
    void assign(UUID committeeUUID, UUID problemUUID) throws ParameterNotFoundException, ResourceNotFoundException;
}

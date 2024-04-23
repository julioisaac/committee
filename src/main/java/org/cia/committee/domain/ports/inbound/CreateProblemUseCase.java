package org.cia.committee.domain.ports.inbound;

import org.cia.committee.common.exception.ParameterNotFoundException;
import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.domain.model.Problem;

import java.util.UUID;

public interface CreateProblemUseCase {
    UUID createProblem(Problem problem) throws ParameterNotFoundException, ResourceNotFoundException;
}

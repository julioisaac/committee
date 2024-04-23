package org.cia.committee.domain.ports.outbound;

import org.cia.committee.common.exception.InfrastructureException;
import org.cia.committee.domain.model.Problem;

import java.util.UUID;

public interface CreateProblemPort {
    UUID createProblem(Problem problem) throws InfrastructureException;
}

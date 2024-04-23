package org.cia.committee.domain.ports.outbound;

import org.cia.committee.common.exception.InfrastructureException;
import org.cia.committee.domain.model.Problem;

public interface UpdateProblemPort {
    void updateProblem(Problem problem) throws InfrastructureException;
}

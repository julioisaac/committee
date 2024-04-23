package org.cia.committee.domain.ports.outbound;

import org.cia.committee.common.exception.InfrastructureException;
import org.cia.committee.domain.model.Problem;

import java.util.List;

public interface UpdateAllProblemsPort {
    void updateAllProblems(List<Problem> problems) throws InfrastructureException;
}

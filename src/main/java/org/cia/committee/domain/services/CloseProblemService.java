package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.domain.model.Problem;
import org.cia.committee.domain.ports.inbound.CloseProblemUseCase;
import org.cia.committee.domain.ports.outbound.FindProblemUUIDPort;
import org.cia.committee.domain.ports.outbound.UpdateProblemPort;

import java.util.UUID;

public class CloseProblemService implements CloseProblemUseCase {

    private final FindProblemUUIDPort findProblemUUIDPort;
    private final UpdateProblemPort updateProblemPort;

    public CloseProblemService(FindProblemUUIDPort findProblemUUIDPort, UpdateProblemPort updateProblemPort) {
        this.findProblemUUIDPort = findProblemUUIDPort;
        this.updateProblemPort = updateProblemPort;
    }


    @Override
    public void close(UUID problemUUID) throws ResourceNotFoundException {

        Problem problem = findProblemUUIDPort.findProblemByUUID(problemUUID)
                .orElseThrow(() -> new ResourceNotFoundException("Problem not found for UUID: " + problemUUID));

        problem.close();

        updateProblemPort.updateProblem(problem);

    }
}

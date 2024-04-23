package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ParameterNotFoundException;
import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.domain.model.Problem;
import org.cia.committee.domain.ports.inbound.AssignProblemUseCase;
import org.cia.committee.domain.ports.outbound.FindProblemUUIDPort;
import org.cia.committee.domain.ports.outbound.UpdateProblemPort;

import java.util.UUID;

public class AssignProblemService implements AssignProblemUseCase {
    private final FindProblemUUIDPort findProblemUUIDPort;
    private final UpdateProblemPort updateProblemPort;

    public AssignProblemService(FindProblemUUIDPort findProblemUUIDPort, UpdateProblemPort updateProblemPort) {
        this.findProblemUUIDPort = findProblemUUIDPort;
        this.updateProblemPort = updateProblemPort;
    }

    @Override
    public void assign(UUID committeeUUID, UUID problemUUID) throws ParameterNotFoundException, ResourceNotFoundException {

        Problem problem = findProblemUUIDPort.findProblemByUUID(problemUUID)
                .orElseThrow(() -> new ResourceNotFoundException("Problem not found for UUID: " + problemUUID));

        assignProblemToCommittee(committeeUUID, problem);

        updateProblemPort.updateProblem(problem);
    }

    public void assignProblemToCommittee(UUID committeeUUID, Problem problem) throws ParameterNotFoundException {
        if (problem.canBeAssigned()) {
            problem.assign(committeeUUID);
        } else {
            String errorMessage = String.format("Cannot assign a problem to committee without Comment. Problem state: %s", problem.getState());
            throw new ParameterNotFoundException(errorMessage);
        }
    }
}

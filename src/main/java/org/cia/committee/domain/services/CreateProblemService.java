package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ParameterNotFoundException;
import org.cia.committee.domain.model.Problem;
import org.cia.committee.domain.ports.inbound.CreateProblemUseCase;
import org.cia.committee.domain.ports.outbound.CreateProblemPort;

import java.util.UUID;

public class CreateProblemService implements CreateProblemUseCase {

    private final CreateProblemPort createProblemPort;

    public CreateProblemService(CreateProblemPort createProblemPort) {
        this.createProblemPort = createProblemPort;
    }

    @Override
    public UUID createProblem(Problem problem) throws ParameterNotFoundException {
        validate(problem);
        return createProblemPort.createProblem(problem);
    }

    private void validate(Problem problem) throws ParameterNotFoundException {
        if (problem.getUserUUID() == null) {
            throw new ParameterNotFoundException("User assigned to the problem must not be null");
        }
        if (problem.getName().isBlank()) {
            throw new ParameterNotFoundException("The problem name must not be empty");
        }
    }
}

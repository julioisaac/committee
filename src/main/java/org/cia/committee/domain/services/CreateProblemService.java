package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ParameterNotFoundException;
import org.cia.committee.domain.events.DomainEvent;
import org.cia.committee.domain.events.EventBuilder;
import org.cia.committee.domain.events.EventType;
import org.cia.committee.domain.model.Problem;
import org.cia.committee.domain.ports.inbound.CreateProblemUseCase;
import org.cia.committee.domain.ports.outbound.CreateProblemPort;
import org.cia.committee.domain.ports.outbound.EventPublisherPort;

import java.util.UUID;

public class CreateProblemService implements CreateProblemUseCase {

    private final CreateProblemPort createProblemPort;
    private final EventPublisherPort eventPublisherPort;

    public CreateProblemService(CreateProblemPort createProblemPort, EventPublisherPort eventPublisherPort) {
        this.createProblemPort = createProblemPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public UUID createProblem(Problem problem) throws ParameterNotFoundException {
        validate(problem);
        UUID problemUUID = createProblemPort.createProblem(problem);

        DomainEvent<Problem> problemCreatedEvent = EventBuilder.<Problem>create().type(EventType.PROBLEM_CREATED).details(problem).build();
        eventPublisherPort.publisher(problemCreatedEvent);

        return problemUUID;
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

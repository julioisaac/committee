package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ParameterNotFoundException;
import org.cia.committee.domain.ports.outbound.EventPublisherPort;
import org.junit.jupiter.api.Test;

import org.cia.committee.domain.model.Problem;
import org.cia.committee.domain.ports.outbound.CreateProblemPort;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CreateProblemServiceTest {

    @Test
    void givenValidUserUUID_whenCreateProblem_shouldCreateProblemSuccessfully() throws ParameterNotFoundException {
        Problem problem = new Problem(UUID.randomUUID(), "Test Problem");
        CreateProblemPort createProblemPort = Mockito.mock(CreateProblemPort.class);
        EventPublisherPort eventPublisherPort = Mockito.mock(EventPublisherPort.class);
        Mockito.when(createProblemPort.createProblem(problem)).thenReturn(UUID.randomUUID());
        CreateProblemService service = new CreateProblemService(createProblemPort, eventPublisherPort);

        UUID createdProblemUUID = service.createProblem(problem);

        assertNotNull(createdProblemUUID);
    }

    @Test
    void givenNullUserUUID_whenCreateProblem_shouldThrowParameterNotFoundException() {
        Problem problem = new Problem(null, "Test Problem");
        CreateProblemPort createProblemPort = Mockito.mock(CreateProblemPort.class);
        EventPublisherPort eventPublisherPort = Mockito.mock(EventPublisherPort.class);
        CreateProblemService service = new CreateProblemService(createProblemPort, eventPublisherPort);

        assertThrows(ParameterNotFoundException.class, () -> service.createProblem(problem));
    }

    @Test
    void giveEmptyProblemName_whenCreateProblem_shouldThrowParameterNotFoundException() {
        Problem problem = new Problem(UUID.randomUUID(), "");
        CreateProblemPort createProblemPort = Mockito.mock(CreateProblemPort.class);
        EventPublisherPort eventPublisherPort = Mockito.mock(EventPublisherPort.class);
        CreateProblemService service = new CreateProblemService(createProblemPort, eventPublisherPort);

        assertThrows(ParameterNotFoundException.class, () -> service.createProblem(problem));
    }
}

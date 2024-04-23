package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.domain.model.Problem;
import org.cia.committee.domain.ports.outbound.FindProblemUUIDPort;
import org.cia.committee.domain.ports.outbound.UpdateProblemPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CloseProblemServiceTest {

    @Test
    void givenAProblem_whenCloseProblem_shouldUpdateProblemSuccessfully() throws ResourceNotFoundException {
        UUID userUUID = UUID.randomUUID();
        Problem problem = new Problem(userUUID, "Problem test");
        FindProblemUUIDPort findProblemUUIDPort = Mockito.mock(FindProblemUUIDPort.class);
        UpdateProblemPort updateProblemPort = Mockito.mock(UpdateProblemPort.class);
        when(findProblemUUIDPort.findProblemByUUID(problem.getUUID())).thenReturn(Optional.of(problem));
        CloseProblemService service = new CloseProblemService(findProblemUUIDPort, updateProblemPort);

        service.close(problem.getUUID());

        verify(findProblemUUIDPort).findProblemByUUID(problem.getUUID());
        verify(updateProblemPort).updateProblem(problem);
    }

    @Test
    void givenAProblem_whenCloseProblem_shouldReturnResourceNotFoundException() {
        UUID problemUUID = UUID.randomUUID();
        FindProblemUUIDPort findProblemUUIDPort = Mockito.mock(FindProblemUUIDPort.class);
        UpdateProblemPort updateProblemPort = Mockito.mock(UpdateProblemPort.class);
        when(findProblemUUIDPort.findProblemByUUID(problemUUID)).thenReturn(Optional.empty());
        CloseProblemService service = new CloseProblemService(findProblemUUIDPort, updateProblemPort);

        assertThrows(ResourceNotFoundException.class, () -> service.close(problemUUID));
    }
}

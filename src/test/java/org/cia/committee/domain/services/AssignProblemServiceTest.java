package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ParameterNotFoundException;
import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.domain.model.Comment;
import org.cia.committee.domain.model.Problem;
import org.cia.committee.domain.ports.outbound.FindProblemUUIDPort;
import org.cia.committee.domain.ports.outbound.UpdateProblemPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AssignProblemServiceTest {


    @Test
    void givenAProblem_whenAssignedToCommittee_shouldUpdateProblemSuccessfully() throws ParameterNotFoundException, ResourceNotFoundException {
        UUID committeeUUID = UUID.randomUUID();
        UUID problemUUID = UUID.randomUUID();
        Problem problem = new Problem(problemUUID, "Problem test");
        Comment comment = new Comment("Comment");
        problem.addComment(comment);
        FindProblemUUIDPort findProblemUUIDPort = Mockito.mock(FindProblemUUIDPort.class);
        UpdateProblemPort updateProblemPort = Mockito.mock(UpdateProblemPort.class);
        when(findProblemUUIDPort.findProblemByUUID(problemUUID)).thenReturn(Optional.of(problem));
        AssignProblemService service = new AssignProblemService(findProblemUUIDPort, updateProblemPort);

        service.assign(committeeUUID, problemUUID);

        verify(findProblemUUIDPort).findProblemByUUID(problemUUID);
        verify(updateProblemPort).updateProblem(problem);
    }
    @Test
    void givenAProblemWithoutComment_whenTryAssign_shouldThrowParameterNotFound() throws ParameterNotFoundException, ResourceNotFoundException {
        UUID committeeUUID = UUID.randomUUID();
        UUID problemUUID = UUID.randomUUID();
        Problem problem = new Problem(problemUUID, "Problem test");
        FindProblemUUIDPort findProblemUUIDPort = Mockito.mock(FindProblemUUIDPort.class);
        UpdateProblemPort updateProblemPort = Mockito.mock(UpdateProblemPort.class);
        when(findProblemUUIDPort.findProblemByUUID(problemUUID)).thenReturn(Optional.of(problem));
        AssignProblemService service = new AssignProblemService(findProblemUUIDPort, updateProblemPort);

        assertThrows(ParameterNotFoundException.class, () -> service.assign(committeeUUID, problemUUID));
    }

    @Test
    void givenAProblem_whenAssignedToCommittee_shouldThrowResourceNotFoundException() {
        UUID committeeUUID = UUID.randomUUID();
        UUID problemUUID = UUID.randomUUID();
        FindProblemUUIDPort findProblemUUIDPort = Mockito.mock(FindProblemUUIDPort.class);
        UpdateProblemPort updateProblemPort = Mockito.mock(UpdateProblemPort.class);
        when(findProblemUUIDPort.findProblemByUUID(problemUUID)).thenReturn(Optional.empty());
        AssignProblemService service = new AssignProblemService(findProblemUUIDPort, updateProblemPort);

        assertThrows(ResourceNotFoundException.class, () -> service.assign(committeeUUID, problemUUID));
    }

}
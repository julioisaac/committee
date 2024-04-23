package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.domain.model.Comment;
import org.cia.committee.domain.model.Problem;
import org.cia.committee.domain.ports.outbound.FindProblemUUIDPort;
import org.cia.committee.domain.ports.outbound.UpdateProblemPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddCommentServiceTest {

    @Test
    void givenAProblem_whenAddComment_shouldUpdateProblemSuccessfully() throws ResourceNotFoundException {
        UUID problemUUID = UUID.randomUUID();
        Comment comment = new Comment("Test comment");
        Problem problem = new Problem(problemUUID, "Test problem");
        FindProblemUUIDPort findProblemUUIDPort = Mockito.mock(FindProblemUUIDPort.class);
        UpdateProblemPort updateProblemPort = Mockito.mock(UpdateProblemPort.class);
        when(findProblemUUIDPort.findProblemByUUID(problemUUID)).thenReturn(Optional.of(problem));
        AddCommentToProblemService addCommentService = new AddCommentToProblemService(findProblemUUIDPort, updateProblemPort);

        addCommentService.addComment(problemUUID, comment);

        verify(findProblemUUIDPort, times(1)).findProblemByUUID(problemUUID);
        verify(updateProblemPort, times(1)).updateProblem(any(Problem.class));
    }

    @Test
    void givenAProblem_whenNotFoundByUUID_shouldNotUpdateProblem() {
        UUID problemUUID = UUID.randomUUID();
        Comment comment = new Comment("Test comment");
        FindProblemUUIDPort findProblemUUIDPort = Mockito.mock(FindProblemUUIDPort.class);
        UpdateProblemPort updateProblemPort = Mockito.mock(UpdateProblemPort.class);
        when(findProblemUUIDPort.findProblemByUUID(problemUUID)).thenReturn(Optional.empty());
        AddCommentToProblemService addCommentService = new AddCommentToProblemService(findProblemUUIDPort, updateProblemPort);

        assertThrows(ResourceNotFoundException.class, () -> addCommentService.addComment(problemUUID, comment));
        verify(findProblemUUIDPort, times(1)).findProblemByUUID(problemUUID);
        verifyNoInteractions(updateProblemPort);
    }

    @Test
    void givenAProblem_whenAddNullComment_shouldReturnResourceNotFoundException() {
        UUID problemUUID = UUID.randomUUID();
        FindProblemUUIDPort findProblemUUIDPort = Mockito.mock(FindProblemUUIDPort.class);
        UpdateProblemPort updateProblemPort = Mockito.mock(UpdateProblemPort.class);
        AddCommentToProblemService addCommentService = new AddCommentToProblemService(findProblemUUIDPort, updateProblemPort);

        assertThrows(ResourceNotFoundException.class, () -> addCommentService.addComment(problemUUID, null));
        verifyNoInteractions(updateProblemPort);
    }
}
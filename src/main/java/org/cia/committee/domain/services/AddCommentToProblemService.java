package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.domain.model.Comment;
import org.cia.committee.domain.model.Problem;
import org.cia.committee.domain.ports.inbound.AddCommentToProblemUseCase;
import org.cia.committee.domain.ports.outbound.FindProblemUUIDPort;
import org.cia.committee.domain.ports.outbound.UpdateProblemPort;

import java.util.UUID;

public class AddCommentToProblemService implements AddCommentToProblemUseCase {

    private final FindProblemUUIDPort findProblemUUIDPort;
    private final UpdateProblemPort updateProblemPort;

    public AddCommentToProblemService(FindProblemUUIDPort findProblemUUIDPort, UpdateProblemPort updateProblemPort) {
        this.findProblemUUIDPort = findProblemUUIDPort;
        this.updateProblemPort = updateProblemPort;
    }
    @Override
    public void addComment(UUID problemUUID, Comment comment) throws ResourceNotFoundException {
        Problem problem = findProblemUUIDPort.findProblemByUUID(problemUUID)
            .orElseThrow(() -> new ResourceNotFoundException("Problem not found for UUID: " + problemUUID));

        problem.addComment(comment);
        updateProblemPort.updateProblem(problem);
    }
}

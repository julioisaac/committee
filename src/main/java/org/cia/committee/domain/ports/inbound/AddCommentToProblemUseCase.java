package org.cia.committee.domain.ports.inbound;

import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.domain.model.Comment;

import java.util.UUID;

public interface AddCommentToProblemUseCase {
    void addComment(UUID problemUUID, Comment comment) throws ResourceNotFoundException;
}
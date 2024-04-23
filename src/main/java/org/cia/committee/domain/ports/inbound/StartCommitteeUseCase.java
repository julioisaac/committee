package org.cia.committee.domain.ports.inbound;

import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.common.exception.UnauthorizedAccessException;
import org.cia.committee.domain.model.User;

import java.util.UUID;

public interface StartCommitteeUseCase {
    void start(User user, UUID committeeUUID) throws UnauthorizedAccessException, ResourceNotFoundException;
}

package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.common.exception.UnauthorizedAccessException;
import org.cia.committee.domain.model.Committee;
import org.cia.committee.domain.model.User;
import org.cia.committee.domain.ports.inbound.AssignCommitteeDirectorUseCase;
import org.cia.committee.domain.ports.outbound.FindCommitteeUUIDPort;
import org.cia.committee.domain.ports.outbound.UpdateCommitteePort;

import java.util.UUID;

public class AssignCommitteeDirectorService implements AssignCommitteeDirectorUseCase, ValidateDirectorRole {

    private final FindCommitteeUUIDPort findCommitteeUUIDPort;
    private final UpdateCommitteePort updateCommitteePort;

    public AssignCommitteeDirectorService(FindCommitteeUUIDPort findCommitteeUUIDPort, UpdateCommitteePort updateCommitteePort) {
        this.findCommitteeUUIDPort = findCommitteeUUIDPort;
        this.updateCommitteePort = updateCommitteePort;
    }

    @Override
    public void assignDirector(User user, UUID committeeUUID) throws UnauthorizedAccessException, ResourceNotFoundException {
        validateDirectorRole(user);

        Committee committee = findCommitteeUUIDPort.findByUUID(committeeUUID)
                .orElseThrow(() -> new ResourceNotFoundException("Committee not found for UUID: " + committeeUUID));

        committee.assignDirector(user.getUUID());
        updateCommitteePort.updateCommittee(committee);
    }
}

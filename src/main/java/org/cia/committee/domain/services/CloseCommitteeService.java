package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.common.exception.UnauthorizedAccessException;
import org.cia.committee.domain.model.Committee;
import org.cia.committee.domain.model.Problem;
import org.cia.committee.domain.model.Role;
import org.cia.committee.domain.model.User;
import org.cia.committee.domain.ports.inbound.CloseCommitteeUseCase;
import org.cia.committee.domain.ports.outbound.FindCommitteeUUIDPort;
import org.cia.committee.domain.ports.outbound.FindProblemsCommitteeUUIDPort;
import org.cia.committee.domain.ports.outbound.UpdateAllProblemsPort;
import org.cia.committee.domain.ports.outbound.UpdateCommitteePort;

import java.util.List;
import java.util.UUID;

public class CloseCommitteeService implements CloseCommitteeUseCase, ValidateDirectorRole {

    private final FindCommitteeUUIDPort findCommitteeUUIDPort;
    private final FindProblemsCommitteeUUIDPort findProblemsCommitteeUUIDPort;
    private final UpdateCommitteePort updateCommitteePort;
    private final UpdateAllProblemsPort updateAllProblemsPort;

    public CloseCommitteeService(FindCommitteeUUIDPort findCommitteeUUIDPort, FindProblemsCommitteeUUIDPort findProblemsCommitteeUUIDPort, UpdateCommitteePort updateCommitteePort, UpdateAllProblemsPort updateAllProblemsPort) {
        this.findCommitteeUUIDPort = findCommitteeUUIDPort;
        this.findProblemsCommitteeUUIDPort = findProblemsCommitteeUUIDPort;
        this.updateCommitteePort = updateCommitteePort;
        this.updateAllProblemsPort = updateAllProblemsPort;
    }

    @Override
    public void close(User user, UUID committeeUUID) throws UnauthorizedAccessException, ResourceNotFoundException {
        validateDirectorRole(user);

        Committee committee = findCommitteeUUIDPort.findByUUID(committeeUUID)
                .orElseThrow(() -> new ResourceNotFoundException("Committee not found for UUID: " + committeeUUID));

        // FIXME implement a transaction manager here
        closeCommittee(committee);
        closeCommitteeProblems(committeeUUID);
    }

    private void closeCommittee(Committee committee) {
        committee.close();
        updateCommitteePort.updateCommittee(committee);
    }

    private void closeCommitteeProblems(UUID committeeUUID) {
        List<Problem> problems = findProblemsCommitteeUUIDPort.findByCommitteeUUID(committeeUUID);
        problems.forEach(Problem::close);
        updateAllProblemsPort.updateAllProblems(problems);
    }

}

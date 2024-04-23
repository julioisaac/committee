package org.cia.committee.domain.ports.outbound;

import org.cia.committee.common.exception.InfrastructureException;
import org.cia.committee.domain.model.Problem;

import java.util.List;
import java.util.UUID;

public interface FindProblemsCommitteeUUIDPort {
    List<Problem> findByCommitteeUUID(UUID committeeUUID) throws InfrastructureException;
}

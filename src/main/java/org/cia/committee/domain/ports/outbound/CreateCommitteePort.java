package org.cia.committee.domain.ports.outbound;

import org.cia.committee.common.exception.InfrastructureException;
import org.cia.committee.domain.model.Committee;

import java.util.UUID;

public interface CreateCommitteePort {

    UUID createCommittee(Committee committee) throws InfrastructureException;
}

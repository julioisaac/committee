package org.cia.committee.domain.ports.inbound;

import org.cia.committee.common.exception.ParameterNotFoundException;
import org.cia.committee.domain.model.Committee;

import java.util.UUID;

public interface CreateCommitteeUseCase {
    UUID createCommittee(Committee committee) throws ParameterNotFoundException;
}

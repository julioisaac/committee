package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ParameterNotFoundException;
import org.cia.committee.domain.model.Committee;
import org.cia.committee.domain.ports.inbound.CreateCommitteeUseCase;
import org.cia.committee.domain.ports.outbound.CreateCommitteePort;

import java.util.UUID;

public class CreateCommitteeService implements CreateCommitteeUseCase {

    private final CreateCommitteePort createCommitteePort;

    public CreateCommitteeService(CreateCommitteePort createCommitteePort) {
        this.createCommitteePort = createCommitteePort;
    }

    @Override
    public UUID createCommittee(Committee committee) throws ParameterNotFoundException {
        validate(committee);
        return this.createCommitteePort.createCommittee(committee);
    }

    private void validate(Committee committee) throws ParameterNotFoundException {
        if (committee.getDate() == null) {
            throw new ParameterNotFoundException("Committee date cannot be null null");
        }
    }
}

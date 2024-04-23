package org.cia.committee.domain.ports.outbound;

import org.cia.committee.common.exception.InfrastructureException;
import org.cia.committee.domain.model.Committee;

public interface UpdateCommitteePort {
    void updateCommittee(Committee committee) throws InfrastructureException;

}

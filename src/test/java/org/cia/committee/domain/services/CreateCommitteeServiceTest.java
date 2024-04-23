package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ParameterNotFoundException;
import org.cia.committee.domain.model.Committee;
import org.cia.committee.domain.ports.outbound.CreateCommitteePort;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCommitteeServiceTest {

    @Test
    void givenDate_whenCreateCommittee_shouldReturnCommitteeUUID() throws ParameterNotFoundException {
        CreateCommitteePort createCommitteePort = mock(CreateCommitteePort.class);
        UUID committeeUUID = UUID.randomUUID();
        Committee committee = new Committee(LocalDate.now());
        when(createCommitteePort.createCommittee(committee)).thenReturn(committeeUUID);
        CreateCommitteeService service = new CreateCommitteeService(createCommitteePort);

        UUID result = service.createCommittee(committee);

        assertEquals(committeeUUID, result);
        verify(createCommitteePort, times(1)).createCommittee(committee);
    }

    @Test
    void giveNullDate_whenCreateCommittee_shouldThrowParameterNotFoundException() {
        Committee committee = new Committee(null);
        CreateCommitteePort createCommitteePort = mock(CreateCommitteePort.class);
        CreateCommitteeService service = new CreateCommitteeService(createCommitteePort);

        assertThrows(ParameterNotFoundException.class, () -> service.createCommittee(committee));
        verify(createCommitteePort, times(0)).createCommittee(committee);
    }
}
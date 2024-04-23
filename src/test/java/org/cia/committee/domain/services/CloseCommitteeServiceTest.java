package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.common.exception.UnauthorizedAccessException;
import org.cia.committee.domain.model.Committee;
import org.cia.committee.domain.model.Role;
import org.cia.committee.domain.model.User;
import org.cia.committee.domain.ports.outbound.FindCommitteeUUIDPort;
import org.cia.committee.domain.ports.outbound.FindProblemsCommitteeUUIDPort;
import org.cia.committee.domain.ports.outbound.UpdateAllProblemsPort;
import org.cia.committee.domain.ports.outbound.UpdateCommitteePort;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class CloseCommitteeServiceTest {

    @Test
    void givenACommittee_whenCloseWithUnauthorizedUser_shouldThrowUnauthorizedAccessException() {
        UUID committeeUUID = UUID.randomUUID();
        User user = new User("Julio");
        FindCommitteeUUIDPort findCommitteeUUIDPort = mock(FindCommitteeUUIDPort.class);
        FindProblemsCommitteeUUIDPort findProblemsCommitteeUUIDPort = mock(FindProblemsCommitteeUUIDPort.class);
        UpdateCommitteePort updateCommitteePort = mock(UpdateCommitteePort.class);
        UpdateAllProblemsPort updateAllProblemsPort = mock(UpdateAllProblemsPort.class);
        when(findCommitteeUUIDPort.findByUUID(committeeUUID)).thenReturn(Optional.empty());
        CloseCommitteeService service = new CloseCommitteeService(findCommitteeUUIDPort, findProblemsCommitteeUUIDPort, updateCommitteePort, updateAllProblemsPort);

        assertThrows(UnauthorizedAccessException.class, () -> service.close(user, committeeUUID));
    }

    @Test
    void givenAEmptyCommittee_whenClose_shouldThrowResourceNotFoundException() {
        UUID committeeUUID = UUID.randomUUID();
        User user = new User("Julio");
        Role directorRole = new Role("DIRECTOR");
        user.addRole(directorRole);
        FindCommitteeUUIDPort findCommitteeUUIDPort = mock(FindCommitteeUUIDPort.class);
        FindProblemsCommitteeUUIDPort findProblemsCommitteeUUIDPort = mock(FindProblemsCommitteeUUIDPort.class);
        UpdateCommitteePort updateCommitteePort = mock(UpdateCommitteePort.class);
        UpdateAllProblemsPort updateAllProblemsPort = mock(UpdateAllProblemsPort.class);
        when(findCommitteeUUIDPort.findByUUID(committeeUUID)).thenReturn(Optional.empty());
        CloseCommitteeService service = new CloseCommitteeService(findCommitteeUUIDPort, findProblemsCommitteeUUIDPort, updateCommitteePort, updateAllProblemsPort);

        assertThrows(ResourceNotFoundException.class, () -> service.close(user, committeeUUID));
    }

    @Test
    void givenACommittee_whenClose_shouldUpdateCommitteeSuccessfully() throws ResourceNotFoundException {
        UUID committeeUUID = UUID.randomUUID();
        Committee committee = new Committee(LocalDate.now());
        User user = new User("Julio");
        Role directorRole = new Role("DIRECTOR");
        user.addRole(directorRole);
        FindCommitteeUUIDPort findCommitteeUUIDPort = mock(FindCommitteeUUIDPort.class);
        FindProblemsCommitteeUUIDPort findProblemsCommitteeUUIDPort = mock(FindProblemsCommitteeUUIDPort.class);
        UpdateCommitteePort updateCommitteePort = mock(UpdateCommitteePort.class);
        UpdateAllProblemsPort updateAllProblemsPort = mock(UpdateAllProblemsPort.class);
        when(findCommitteeUUIDPort.findByUUID(committeeUUID)).thenReturn(Optional.of(committee));
        CloseCommitteeService service = new CloseCommitteeService(findCommitteeUUIDPort, findProblemsCommitteeUUIDPort, updateCommitteePort, updateAllProblemsPort);

        service.close(user, committeeUUID);

        verify(findCommitteeUUIDPort).findByUUID(committeeUUID);
        verify(updateCommitteePort).updateCommittee(committee);
    }
}
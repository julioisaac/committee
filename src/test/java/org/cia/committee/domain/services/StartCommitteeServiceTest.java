package org.cia.committee.domain.services;

import org.cia.committee.common.exception.ResourceNotFoundException;
import org.cia.committee.common.exception.UnauthorizedAccessException;
import org.cia.committee.domain.model.Committee;
import org.cia.committee.domain.model.Role;
import org.cia.committee.domain.model.User;
import org.cia.committee.domain.ports.outbound.FindCommitteeUUIDPort;
import org.cia.committee.domain.ports.outbound.UpdateCommitteePort;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class StartCommitteeServiceTest {

    @Test
    void givenACommittee_whenStartWithUnauthorizedUser_shouldThrowUnauthorizedAccessException() {
        UUID committeeUUID = UUID.randomUUID();
        User user = new User("Julio");
        FindCommitteeUUIDPort findCommitteeUUIDPort = mock(FindCommitteeUUIDPort.class);
        UpdateCommitteePort updateCommitteePort = mock(UpdateCommitteePort.class);
        when(findCommitteeUUIDPort.findByUUID(committeeUUID)).thenReturn(Optional.empty());
        StartCommitteeService service = new StartCommitteeService(findCommitteeUUIDPort, updateCommitteePort);

        assertThrows(UnauthorizedAccessException.class, () -> service.start(user, committeeUUID));
    }

    @Test
    void givenAEmptyCommittee_whenStart_shouldThrowResourceNotFoundException() {
        UUID committeeUUID = UUID.randomUUID();
        User user = new User("Julio");
        Role directorRole = new Role("DIRECTOR");
        user.addRole(directorRole);
        FindCommitteeUUIDPort findCommitteeUUIDPort = mock(FindCommitteeUUIDPort.class);
        UpdateCommitteePort updateCommitteePort = mock(UpdateCommitteePort.class);
        when(findCommitteeUUIDPort.findByUUID(committeeUUID)).thenReturn(Optional.empty());
        StartCommitteeService service = new StartCommitteeService(findCommitteeUUIDPort, updateCommitteePort);

        assertThrows(ResourceNotFoundException.class, () -> service.start(user, committeeUUID));
    }

    @Test
    void givenACommittee_whenStart_shouldUpdateCommitteeSuccessfully() throws ResourceNotFoundException {
        UUID committeeUUID = UUID.randomUUID();
        Committee committee = new Committee(LocalDate.now());
        User user = new User("Julio");
        Role directorRole = new Role("DIRECTOR");
        user.addRole(directorRole);
        FindCommitteeUUIDPort findCommitteeUUIDPort = mock(FindCommitteeUUIDPort.class);
        UpdateCommitteePort updateCommitteePort = mock(UpdateCommitteePort.class);
        when(findCommitteeUUIDPort.findByUUID(committeeUUID)).thenReturn(Optional.of(committee));
        StartCommitteeService service = new StartCommitteeService(findCommitteeUUIDPort, updateCommitteePort);

        service.start(user, committeeUUID);

        verify(findCommitteeUUIDPort).findByUUID(committeeUUID);
        verify(updateCommitteePort).updateCommittee(committee);
    }
}
package org.cia.committee.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CommitteeTest {

    @Test
    void givenADate_whenCreateACommittee_thenACommitteeIsReturned() {
        LocalDate committeeDate = LocalDate.now();

        Committee committee = new Committee(committeeDate);

        Assertions.assertNotNull(committee.getUUID());
        assertEquals(CommitteeState.PLANNED, committee.getState());
    }

    @Test
    void givenACommitteeCreated_whenItIsStarted_thenTheCommitteeStateMustBeChanged() {
        LocalDate committeeDate = LocalDate.now();

        Committee committee = new Committee(committeeDate);
        committee.assignDirector(UUID.randomUUID());
        committee.start();

        assertEquals(CommitteeState.IN_PROGRESS, committee.getState());
    }

    @Test
    void givenACommitteeCreated_whenItIsClosed_thenTheCommitteeStateMustBeChanged() {
        LocalDate committeeDate = LocalDate.now();

        Committee committee = new Committee(committeeDate);
        committee.start();

        committee.close();

        assertEquals(CommitteeState.CLOSED, committee.getState());
    }
}
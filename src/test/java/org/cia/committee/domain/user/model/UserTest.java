package org.cia.committee.domain.user.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void givenAName_whenCreateAUser_thenARoleCouldBeAssigned() {
        String userName = "Julio";
        User user = new User(userName);

        Assertions.assertNotNull(user.getUUID());
        Assertions.assertEquals(userName, user.getName());
    }

    @Test
    void givenARole_whenAssignedToUser_thenTheRoleCouldBeChecked() {
        Role directorRole = new Role("DIRECTOR");

        String userName = "Julio";
        User user = new User(userName);

        user.addRole(directorRole);

        Assertions.assertTrue(user.hasRole(directorRole));
    }

}
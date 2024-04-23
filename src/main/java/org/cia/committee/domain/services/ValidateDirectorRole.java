package org.cia.committee.domain.services;

import org.cia.committee.common.exception.UnauthorizedAccessException;
import org.cia.committee.domain.model.Role;
import org.cia.committee.domain.model.User;

public interface ValidateDirectorRole {

    Role DIRECTOR_ROLE = new Role("DIRECTOR");

    default void validateDirectorRole(User user) throws UnauthorizedAccessException {
        if (!user.hasRole(DIRECTOR_ROLE)) {
            throw new UnauthorizedAccessException("User is not director");
        }
    }
}

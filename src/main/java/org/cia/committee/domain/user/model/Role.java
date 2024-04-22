package org.cia.committee.domain.user.model;

import java.util.UUID;

public class Role {
    private UUID uuid;

    private String name;

    public Role(String name) {
        this.name = name;
    }
}

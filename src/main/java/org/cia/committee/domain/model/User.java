package org.cia.committee.domain.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class User {

    private UUID uuid;

    private String name;

    private Set<Role> roles;

    private LocalDateTime createdAt;

    public User(String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.roles = new HashSet<>();
        this.createdAt = LocalDateTime.now();
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public boolean hasRole(Role role) {
        return this.roles.contains(role);
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}

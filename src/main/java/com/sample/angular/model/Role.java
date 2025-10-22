package com.sample.angular.model;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permissions.TODO_DELETE,Permissions.TODO_WRITE,Permissions.TODO_READ)),
    USER(Set.of(Permissions.TODO_READ));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
package com.manicure.app.domain.enuns;

import lombok.Getter;

@Getter
public enum UserRole {
    AI("ai"),
    ADMIN("admin");

    private String role;

    UserRole(String role){
        this.role = role;
    }
}

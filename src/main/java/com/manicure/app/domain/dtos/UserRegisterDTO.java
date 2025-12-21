package com.manicure.app.domain.dtos;

import com.manicure.app.domain.enuns.UserRole;

public record UserRegisterDTO(String login, String password, UserRole role) {
}

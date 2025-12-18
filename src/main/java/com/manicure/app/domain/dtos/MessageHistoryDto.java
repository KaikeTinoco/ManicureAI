package com.manicure.app.domain.dtos;

import java.time.LocalDateTime;

public record MessageHistoryDto(
        String content,
        LocalDateTime creationTimestamp,
        boolean isFromClient
) {}


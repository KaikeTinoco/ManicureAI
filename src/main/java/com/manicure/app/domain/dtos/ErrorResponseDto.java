package com.manicure.app.domain.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ErrorResponseDto {
    private Integer statusCode;
    private String message;
    private List<String> messages;
}

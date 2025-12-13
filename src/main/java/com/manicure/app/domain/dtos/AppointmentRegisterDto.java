package com.manicure.app.domain.dtos;

import com.manicure.app.domain.enuns.Services;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AppointmentRegisterDto {
    private String clientName;

    private LocalTime time;

    private LocalDate date;

    private Services service;
}

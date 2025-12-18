package com.manicure.app.domain.dtos;

import com.manicure.app.domain.enuns.Services;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRegisterDto {
    private String clientName;

    private LocalTime time;

    private LocalDate date;

    private Services service;
}

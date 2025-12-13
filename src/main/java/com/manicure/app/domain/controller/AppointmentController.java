package com.manicure.app.domain.controller;

import com.manicure.app.domain.dtos.AppointmentRegisterDto;
import com.manicure.app.domain.entities.Appointment;
import com.manicure.app.domain.enuns.Services;
import com.manicure.app.domain.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController("/appointments")
public class AppointmentController {
    private final AppointmentService service;

    @Autowired
    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Appointment> registerAppointment(@RequestBody AppointmentRegisterDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerAppointment(dto));
    }

    @GetMapping(path = "/listAll")
    public ResponseEntity<List<Appointment>> findAll(){
        return ResponseEntity.ok(service.listAllAppointments());
    }

    @GetMapping(path = "/listByDate")
    public ResponseEntity<List<Appointment>> listAllByDate(@RequestBody LocalDate date){
        return ResponseEntity.ok(service.listAllAppointmentsByDate(date));
    }

    @GetMapping(path = "/listByService")
    public ResponseEntity<List<Appointment>> listAllByService(@RequestBody Services services){
        return ResponseEntity.ok(service.listAllAppointmentsByService(services));
    }

    @DeleteMapping(path = "/delete")
    public void deleteAppointment(@RequestParam UUID id){
        service.deleteAppointment(id);
    }

}

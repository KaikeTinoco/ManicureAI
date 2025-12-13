package com.manicure.app.domain.services;

import com.manicure.app.domain.dtos.AppointmentRegisterDto;
import com.manicure.app.domain.entities.Appointment;
import com.manicure.app.domain.entities.Client;
import com.manicure.app.domain.enuns.Services;
import com.manicure.app.domain.exceptions.EmptyPropertyException;
import com.manicure.app.domain.exceptions.NotFoundException;
import com.manicure.app.domain.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentService {
    private final AppointmentRepository repository;
    private final ClientService clientService;

    @Autowired
    public AppointmentService(AppointmentRepository repository, ClientService clientService) {
        this.repository = repository;
        this.clientService = clientService;
    }

    public Appointment registerAppointment(AppointmentRegisterDto dto){
        Client client =  clientService.findByName(dto.getClientName());
        Appointment appointment = Appointment.builder()
                .time(dto.getTime())
                .date(dto.getDate())
                .service(dto.getService())
                .clientName(dto.getClientName().strip().toLowerCase())
                .client(client)
                .creationDate(LocalDateTime.now())
                .build();
        repository.save(appointment);
        clientService.addAppointmentToList(client, appointment);
        return appointment;
    }

    public void deleteAppointment(UUID id){
        if(id == null){
            throw new EmptyPropertyException("por favor, informe um id");
        }
        Appointment a = repository.findById(id).orElseThrow(
                ()-> new NotFoundException("Não foi possível encontrar um agendamento com esse id")
        );
        repository.delete(a);
    }

    public List<Appointment> listAllAppointmentsByDate(LocalDate date){
        if(repository.findByDate(date).isPresent()){
            return repository.findByDate(date).get();
        } else {
            return new ArrayList<>();
        }
    }

    public List<Appointment> listAllAppointmentsByService(Services service){
        if(repository.findByService(service).isPresent()){
            return repository.findByService(service).get();
        } else {
            return new ArrayList<>();
        }
    }

    public List<Appointment> listAllAppointments(){
        return repository.findAll();
    }


}

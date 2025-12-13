package com.manicure.app.domain.services;

import com.manicure.app.domain.dtos.ClientRegisterDto;
import com.manicure.app.domain.entities.Appointment;
import com.manicure.app.domain.entities.Client;
import com.manicure.app.domain.exceptions.BadRequestException;
import com.manicure.app.domain.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {


    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client saveClient(ClientRegisterDto dto) {
        if(dto.getClientName() == null ||dto.getWhatsappId() == null){
            throw new com.manicure.app.domain.exceptions.BadRequestException("por favor preencha todos os campos");
        }
        Client client = Client.builder()
                .clientName(dto.getClientName().strip().toLowerCase())
                .whatsappId(dto.getWhatsappId())
                .creationDate(LocalDateTime.now())
                .appointments(new ArrayList<>())
                .build();

        repository.save(client);
        return client;
    }

    public List<Client> getAllClients(){
        return repository.findAll();
    }

    public Client findByName(String clientName){
        if(clientName == null){
            throw new BadRequestException("Por favor informe um nome válido");
        }
        return repository.findByName(clientName).orElseThrow(
                ()-> new BadRequestException("Não foi possível encontrar um cliente com esse nome")
        );
    }

    public Client findById(UUID id) {
        return repository.findById(id).orElseThrow(
                ()-> new BadRequestException("por favor, informe um ID válido")
        );
    }

    public Client findByWhatsapId(String whatsappId) {
        return repository.findByWhatsappId(whatsappId).orElseThrow(
                ()-> new BadRequestException("por favor, informe um ID de whatsapp válido")
        );
    }

    public void deleteClient(String whatsappId, UUID id)  {
        Client c;
        if(!validateParams(whatsappId, id)){
            throw new BadRequestException("por favor, informe pelo menos um campo necessário para a busca");
        }
        if(whatsappId == null){
            c = repository.findById(id).orElseThrow(
                    () -> new BadRequestException("por favor, informe um ID válido")
            );
        } else {
            c = repository.findByWhatsappId(whatsappId).orElseThrow(
                    () -> new BadRequestException("por favor, informe um ID de whatsapp válido")
            );
        }
        repository.delete(c);
    }

    public List<Appointment> getClientAppointmentsList(String whatsappId, UUID id){
        Client c;
        if(!validateParams(whatsappId, id)){
            throw new BadRequestException("por favor, informe pelo menos um campo necessário para a busca");
        }
        if(whatsappId == null){
            c = repository.findById(id).orElseThrow(
                    () -> new BadRequestException("por favor, informe um ID válido")
            );
        } else {
            c = repository.findByWhatsappId(whatsappId).orElseThrow(
                    () -> new BadRequestException("por favor, informe um ID de whatsapp válido")
            );
        }
        return c.getAppointments();
    }

    private Boolean validateParams(String whatsappId, UUID id){
        return !(whatsappId == null && id == null);
    }

    public void addAppointmentToList(Client c, Appointment a){
        List<Appointment> appointments = c.getAppointments();
        appointments.add(a);
        c.setAppointments(appointments);
        repository.save(c);
    }
}

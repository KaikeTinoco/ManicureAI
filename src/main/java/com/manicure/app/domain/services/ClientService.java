package com.manicure.app.domain.services;

import com.manicure.app.domain.dtos.ClientRegisterDto;
import com.manicure.app.domain.entities.Client;
import com.manicure.app.domain.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
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
                .clientName(dto.getClientName())
                .whatsappId(dto.getWhatsappId())
                .creationDate(LocalDateTime.now())
                .build();

        repository.save(client);
        return client;
    }

    public List<Client> getAllClients(){
        return repository.findAll();
    }

    public Client findById(UUID id) {
        return repository.findById(id).orElseThrow(
                ()-> new com.manicure.app.domain.exceptions.BadRequestException("por favor, informe um ID válido")
        );
    }

    public Client findByWhatsapId(String whatsappId) {
        return repository.findByWhatsappId(whatsappId).orElseThrow(
                ()-> new com.manicure.app.domain.exceptions.BadRequestException("por favor, informe um ID de whatsapp válido")
        );
    }

    public void deleteClient(String whatsappId, UUID id)  {
        Client c;
        if(whatsappId == null){
            c = repository.findById(id).orElseThrow(
                    () -> new com.manicure.app.domain.exceptions.BadRequestException("por favor, informe um ID válido")
            );
        } else {
            c = repository.findByWhatsappId(whatsappId).orElseThrow(
                    () -> new com.manicure.app.domain.exceptions.BadRequestException("por favor, informe um ID de whatsapp válido")
            );
        }
        repository.delete(c);
    }
}

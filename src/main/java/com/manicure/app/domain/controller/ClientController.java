package com.manicure.app.domain.controller;

import com.manicure.app.domain.dtos.ClientRegisterDto;
import com.manicure.app.domain.entities.Appointment;
import com.manicure.app.domain.entities.Client;
import com.manicure.app.domain.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/clients")
public class ClientController {
    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }


    @PostMapping(path = "/register")
    public ResponseEntity<Client> registerClient(@RequestBody ClientRegisterDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveClient(dto));
    }

    @GetMapping(path = "/findById")
    public ResponseEntity<Client> findClientById(@RequestParam UUID id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping(path = "/findByWhatsappId")
    public ResponseEntity<Client> findByWhatsappId(@RequestParam String whatsappId){
        return ResponseEntity.ok(service.findByWhatsapId(whatsappId));
    }

    @DeleteMapping(path = "/delete")
    public void deleteClient(@RequestParam(required = false) UUID id,
                             @RequestParam(required = false) String whatsappId){
        service.deleteClient(whatsappId, id);
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<Client>> findAll(){
        return ResponseEntity.ok(service.getAllClients());
    }

    @GetMapping(path = "/listAppointments")
    public ResponseEntity<List<Appointment>> listAppointments(@RequestParam(required = false) UUID id,
                                                              @RequestParam(required = false) String whatsappId){
        return ResponseEntity.ok(service.getClientAppointmentsList(whatsappId, id));
    }


}

package com.manicure.app.domain.controller;

import com.manicure.app.domain.dtos.MessageRegisterDto;
import com.manicure.app.domain.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService service;

    @Autowired
    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping(path = "/register")
    public void registerMessage(@RequestBody MessageRegisterDto dto){
        service.registerMessage(dto);
    }

    @GetMapping(path = "/history")
    public ResponseEntity<String> listLastTenMessages(@RequestParam String whatsappId){
        return ResponseEntity.ok(service.getFormattedHistoryForAI(whatsappId));
    }
}

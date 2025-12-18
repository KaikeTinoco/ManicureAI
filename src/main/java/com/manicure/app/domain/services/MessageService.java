package com.manicure.app.domain.services;

import com.manicure.app.domain.dtos.MessageHistoryDto;
import com.manicure.app.domain.dtos.MessageRegisterDto;
import com.manicure.app.domain.entities.Client;
import com.manicure.app.domain.entities.Message;
import com.manicure.app.domain.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository repository;
    private final ClientService clientService;

    @Autowired
    public MessageService(MessageRepository repository, ClientService clientService) {
        this.repository = repository;
        this.clientService = clientService;
    }

    public Message registerMessage(MessageRegisterDto dto){
        Client c = clientService.findByWhatsapId(dto.getWhatsappId());
           Message message = Message.builder()
                   .content(dto.getContent())
                   .creationTimestamp(LocalDateTime.now())
                   .isFromClient(!dto.isMessageSentByClient())
                   .build();
           c.addMessage(message);
           repository.save(message);
           return message;
    }


    public String getFormattedHistoryForAI(String whatsappId) {
        Client client = clientService.findByWhatsapId(whatsappId);
        if (client == null) {
            return "HISTÓRICO: Cliente novo, sem histórico de conversas.";
        }

        List<MessageHistoryDto> historyList =
                repository.findTop10ByClientIdOrderByCreationTimestampDesc(client.getId());

        if (historyList.isEmpty()) {
            return "HISTÓRICO: Cliente sem mensagens prévias.";
        }

        StringBuilder history = new StringBuilder("### HISTÓRICO DA CONVERSA (Últimas 10 interações):\n");

        for (int i = historyList.size() - 1; i >= 0; i--) {
            MessageHistoryDto dto = historyList.get(i);

            String prefix = dto.isFromClient() ? "Cliente" : "Agente";

            DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
            String timestamp = dto.creationTimestamp().format(parser);

            history.append("- [")
                    .append(prefix)
                    .append(" às ")
                    .append(timestamp)
                    .append("]: ")
                    .append(dto.content())
                    .append("\n");


            String textoFormatado = history.toString();

            return "{\"historico\": \"" + textoFormatado.replace("\n", "\\n")
                    .replace("\"", "\\\"") + "\"}";
        }

        return history.toString();
    }
}

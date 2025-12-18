package com.manicure.app.domain.repositories;

import com.manicure.app.domain.dtos.MessageHistoryDto;
import com.manicure.app.domain.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<MessageHistoryDto> findTop10ByClientIdOrderByCreationTimestampDesc(UUID clienteId);
}


package com.manicure.app.domain.repositories;

import com.manicure.app.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientRepository, UUID> {
    Optional<Client> findByWhatsappId(String whatsappId);
    Optional<Client> findByName(String name);
}

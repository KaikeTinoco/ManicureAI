package com.manicure.app.domain.repositories;

import com.manicure.app.domain.entities.Appointment;
import com.manicure.app.domain.enuns.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    Optional<List<Appointment>> findByDate (LocalDate date);
    Optional<List<Appointment>> findByService(Services service);
}

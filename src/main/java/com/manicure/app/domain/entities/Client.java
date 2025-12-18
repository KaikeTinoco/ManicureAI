package com.manicure.app.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String whatsappId;
    private String clientName;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    private LocalDateTime creationDate;

    public void addAppointment(Appointment a){
        appointments.add(a);
        a.setClient(this);
    }

    public void removeAppointment(Appointment a){
        appointments.remove(a);
        a.setClient(null);
    }

    public void addMessage(Message m){
        messages.add(m);
        m.setClient(this);
    }

    public void removeMessage(Message m){
        messages.remove(m);
        m.setClient(null);
    }

}

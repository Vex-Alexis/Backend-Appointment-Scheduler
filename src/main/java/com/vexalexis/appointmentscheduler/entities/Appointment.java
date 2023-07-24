package com.vexalexis.appointmentscheduler.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientID;
    private String doctorId;
    private String specialtyId;

    @Future(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    @NotBlank(message = "La fecha de la cita medica es obligatorio")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime appointmentDate;

    @Size(max = 1000, message = "Las notas no pueden exceder los 1000 caracteres")
    @Column(length = 1000)
    private String notes;

    private LocalDateTime createdAt;




    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }


}

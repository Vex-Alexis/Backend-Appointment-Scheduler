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

    @Future(message = "La fecha de la cita medica debe ser posterior a la fecha actual")
    @NotBlank(message = "La fecha de la cita medica es obligatoria")
    @Column(nullable = false, columnDefinition = "TIMESTAMP(0)")
    private LocalDateTime appointmentDate;

    @Size(max = 1000, message = "Las notas no pueden exceder los 1000 caracteres")
    @Column(length = 1000)
    private String notes;
    private LocalDateTime createdAt;


    // TODO: Entidades relacionadas a la entidad "Appointment"

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;





    // TODO: Metodos adicionales

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }


}

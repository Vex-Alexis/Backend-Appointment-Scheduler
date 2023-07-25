package com.vexalexis.appointmentscheduler.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido no puede tener más de 50 caracteres")
    private String lastName;

    @NotBlank(message = "La cédula es obligatoria")
    @Pattern(regexp = "^[1-9]\\d{3,11}$", message = "La cédula debe tener entre 4 y 12 dígitos y no puede contener ceros a la izquierda")
    @Column(unique = true)
    private String cedula;

    @Email(message = "El correo electrónico no es válido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "El consultorio no puede estar en blanco")
    @Size(min = 1, max = 50, message = "El consultorio debe tener entre 1 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9\\s]+$", message = "El consultorio solo puede contener letras, números y espacios")
    private String office;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



    // TODO: Entidades relacionadas a la entidad "Doctor"

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "doctor_specialty",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<Specialty> specialties = new HashSet<>();


    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments = new ArrayList<>();



    // TODO: Metodos adicionales

    // Método para generar la fecha automática al crear el registro antes de persistirlo
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    // Método para generar la fecha automática al crear el actualizar antes de persistirlo
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        // Otras acciones antes de la actualización...
    }


}

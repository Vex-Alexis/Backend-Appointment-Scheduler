package com.vexalexis.appointmentscheduler.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "patient")
public class Patient {

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

    @NotBlank(message = "El número de teléfono no puede estar en blanco")
    @Pattern(regexp = "^[0-9]{10}$", message = "El número de teléfono debe tener 10 dígitos numéricos")
    private String phoneNumber;

    @Email(message = "El correo electrónico no es válido")
    @Column(unique = true)
    private String email;

    @NotNull(message = "La fecha de nacimiento no puede estar en blanco")
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    private LocalDate dateOfBirth;

    @NotBlank(message = "El género no puede estar en blanco")
    @Pattern(regexp = "^(Masculino|Femenino)$", message = "El género debe ser 'Masculino' o 'Femenino'")
    private String gender;

    @Size(max = 200, message = "La dirección no puede tener más de 200 caracteres")
    private String address;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;





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

    // Formatear fecha de nacimiento al obtenerla
    public String getDateOfBirthFormatted() {
        if (dateOfBirth != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return dateOfBirth.format(formatter);
        }
        return null;
    }

}

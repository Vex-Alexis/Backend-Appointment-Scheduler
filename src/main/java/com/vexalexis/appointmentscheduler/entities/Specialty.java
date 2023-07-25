package com.vexalexis.appointmentscheduler.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "specialty")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la especialidad es obligatorio")
    @Size(max = 80, message = "Su nombre no puede tener más de 80 caracteres")
    private String name;


    // TODO: Entidades relacionadas a la entidad "Specialty"

    @ManyToMany(mappedBy = "specialties", cascade = CascadeType.ALL)
    private List<Doctor> doctors = new ArrayList<>();


}

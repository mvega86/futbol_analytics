package com.futbol.analytics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "partidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El equipo local no puede estar vacío")
    private String equipoLocal;

    @NotBlank(message = "El equipo visitante no puede estar vacío")
    private String equipoVisitante;

    @Min(value = 0, message = "Los goles no pueden ser negativos")
    private int golesLocal;

    @Min(value = 0, message = "Los goles no pueden ser negativos")
    private int golesVisitante;

    @NotNull(message = "La fecha del partido no puede ser nula")
    private Date fecha;
    // Getters y Setters
}


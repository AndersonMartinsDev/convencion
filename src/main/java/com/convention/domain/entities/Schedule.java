package com.convention.domain.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "dateTimeExecution")
    private LocalDateTime dateTimeExecution;
    @NotEmpty
    @Column(name = "theme")
    private String theme;
    @Column(name = "timeInMinutes")
    private Integer timeInMinutes;

}

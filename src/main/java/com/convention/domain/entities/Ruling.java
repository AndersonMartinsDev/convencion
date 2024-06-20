package com.convention.domain.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name ="ruling")
public class Ruling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "theme",nullable = false)
    private String theme;
    @Column(name = "order_priority")
    private Integer orderPriority;
    @Column(name = "timeInMinutes")
    private Integer timeInMinutes;
}

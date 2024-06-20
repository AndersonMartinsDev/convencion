package com.convention.domain.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "document", nullable = false,unique = true)
    private String document;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email", nullable = false,unique = true)
    private String email;
}

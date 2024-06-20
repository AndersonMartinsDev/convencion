package com.convention.domain.entities;


import com.convention.domain.entities.enums.ConferenceTypeEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "conference")
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "dateTimeExecution", nullable = false)
    private LocalDate dateTimeExecution;
    @Column(name = "convener", nullable = false)
    private String convener;
    @Column(name = "minMembers", nullable = false)
    private Integer quorum;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ConferenceTypeEnum conferenceTypeEnum;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Member> membersList;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Ruling> rulingList;
}

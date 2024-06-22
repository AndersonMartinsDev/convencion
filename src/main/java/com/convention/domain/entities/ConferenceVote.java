package com.convention.domain.entities;

import com.convention.domain.entities.enums.ConferenceVoteEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@Table(name="conference_vote")
public class ConferenceVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(unique = true,nullable = false)
    private Schedule schedule;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false)
    private Member member;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="vote")
    private ConferenceVoteEnum vote;

    //TODO: RESOLVER PROBLEMA DE VOTAÇão dois usuários diferentes não votam na mesma pauta
}

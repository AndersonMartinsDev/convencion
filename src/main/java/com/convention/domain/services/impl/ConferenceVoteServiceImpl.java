package com.convention.domain.services.impl;

import com.convention.application.dto.vote.ConferenceVoteDto;
import com.convention.domain.entities.ConferenceVote;
import com.convention.domain.exceptions.ConvencionException;
import com.convention.domain.repositories.ConferenceVoteRepository;
import com.convention.domain.repositories.MemberRepository;
import com.convention.domain.services.ConferenceVoteService;
import com.convention.domain.services.ScheduleService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.convention.domain.entities.enums.StatusDocumentsEnum.ABLE_TO_VOTE;
import static com.convention.domain.exceptions.ConvenctionTextExecptions.*;

@Service
public class ConferenceVoteServiceImpl implements ConferenceVoteService {

    private final ScheduleService scheduleService;
    private final ConferenceVoteRepository repository;

    private final CpfCheckServiceFutureImpl clientService;
    private final MemberRepository memberRepository;

    @Autowired
    public ConferenceVoteServiceImpl(ScheduleService scheduleService, ConferenceVoteRepository repository, CpfCheckServiceFutureImpl clientService, MemberRepository memberRepository) {
        this.scheduleService = scheduleService;
        this.repository = repository;
        this.clientService = clientService;
        this.memberRepository = memberRepository;
    }

    @Override
    @SneakyThrows
    public ConferenceVote insert(ConferenceVoteDto conferenceVote) {

        var external = clientService.verifyMember(conferenceVote.getDocument()).get();

        var member = memberRepository.findByDocument(conferenceVote.getDocument());

        if (Objects.isNull(member) || Objects.isNull(external) || external.get("status") != ABLE_TO_VOTE) {
            throw new ConvencionException(DOCUMENT_UNABLE_TO_VOTE);
        }

        var conference = ConferenceVote
                .builder()
                .memberId(member.getId())
                .scheduleId(conferenceVote.getScheduleId())
                .build();

        Boolean existVote = repository.exists(Example.of(conference));

        if (scheduleService.verifyIfCanApplyVote(conferenceVote.getScheduleId())) {
            throw new ConvencionException(UNABLE_TO_VOTE);
        }

        if (existVote) {
            throw new ConvencionException(ALREADY_VOTE);
        }

        conference.setVote(conferenceVote.getVote());
        return repository.save(conference);
    }

    @SneakyThrows
    public void delete(Long index) {
        repository.deleteById(index);
    }

}

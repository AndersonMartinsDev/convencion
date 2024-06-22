package com.convention.domain.services.impl;

import com.convention.domain.entities.ConferenceVote;
import com.convention.domain.exceptions.ConvencionException;
import com.convention.domain.exceptions.ConvenctionTextExecptions;
import com.convention.domain.repositories.ConferenceVoteRepository;
import com.convention.domain.services.ConferenceVoteService;
import com.convention.domain.services.ScheduleService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class ConferenceVoteServiceImpl implements ConferenceVoteService {

    private final ScheduleService scheduleService;
    private final ConferenceVoteRepository repository;

    public ConferenceVoteServiceImpl(ScheduleService scheduleService, ConferenceVoteRepository repository) {
        this.scheduleService = scheduleService;
        this.repository = repository;
    }

    @Override
    @SneakyThrows
    public ConferenceVote insert(ConferenceVote conferenceVote) {
        if(scheduleService.verifyIfCanApplyVote(conferenceVote.getSchedule().getId())){
            throw new ConvencionException(ConvenctionTextExecptions.UNABLE_TO_VOTE);
        }
        return repository.save(conferenceVote);
    }

    @SneakyThrows
    public void delete(Long index){
         repository.deleteById(index);
    }
}

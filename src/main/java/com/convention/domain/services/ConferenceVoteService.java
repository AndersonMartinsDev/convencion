package com.convention.domain.services;

import com.convention.application.dto.vote.ConferenceVoteDto;
import com.convention.domain.entities.ConferenceVote;

public interface ConferenceVoteService {
    ConferenceVote insert(ConferenceVoteDto conferenceVote);

    void delete(Long index);
}

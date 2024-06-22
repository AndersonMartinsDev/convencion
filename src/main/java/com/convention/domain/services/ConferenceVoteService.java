package com.convention.domain.services;

import com.convention.domain.entities.ConferenceVote;

public interface ConferenceVoteService {
    ConferenceVote insert(ConferenceVote conferenceVote);

    void delete(Long index);
}

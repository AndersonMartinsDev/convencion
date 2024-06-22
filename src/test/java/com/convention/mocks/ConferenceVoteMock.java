package com.convention.mocks;

import com.convention.application.dto.vote.ConferenceVoteDto;
import com.convention.application.dto.vote.ConferenceVoteResumeDto;
import com.convention.domain.entities.ConferenceVote;
import com.convention.domain.entities.enums.ConferenceVoteEnum;

public class ConferenceVoteMock {
    public static ConferenceVoteResumeDto createConferenceVoteResumeDto() {
        return ConferenceVoteResumeDto
                .builder()
                .vote(ConferenceVoteEnum.SIM)
                .build();
    }

    public static ConferenceVoteDto createConferenceVoteDto() {
        return ConferenceVoteDto
                .builder()
                .document("13908123081")
                .scheduleId(1L)
                .vote(ConferenceVoteEnum.SIM)
                .build();
    }

    public static ConferenceVote createConferenceVote() {
        return ConferenceVote
                .builder()
                .id(1l)
                .scheduleId(1l)
                .memberId(1l)
                .vote(ConferenceVoteEnum.SIM)
                .build();
    }
}

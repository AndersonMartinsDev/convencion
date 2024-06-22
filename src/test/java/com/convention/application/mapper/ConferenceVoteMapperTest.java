package com.convention.application.mapper;

import com.convention.application.dto.vote.ConferenceVoteDto;
import com.convention.application.dto.vote.ConferenceVoteResumeDto;
import com.convention.domain.entities.ConferenceVote;
import com.convention.domain.entities.enums.ConferenceVoteEnum;
import com.convention.mocks.ConferenceVoteMock;
import org.junit.jupiter.api.Test;

import static com.convention.mocks.ConferenceVoteMock.createConferenceVote;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ConferenceVoteMapperTest {

    @Test
    public void testToConferenceVoteWithValidDto() {
        Long memberId = 1L;
        Long scheduleId = 1L;
        ConferenceVoteEnum vote = ConferenceVoteEnum.SIM;

        ConferenceVoteDto dto = ConferenceVoteMock.createConferenceVoteDto();

        ConferenceVote conferenceVote = ConferenceVoteMapper.toConferenceVote(dto);

        assertEquals(scheduleId, conferenceVote.getScheduleId());
        assertEquals(vote, conferenceVote.getVote());
    }

    @Test
    public void testToConferenceVoteWithNullDto() {
        assertNull(ConferenceVoteMapper.toConferenceVote(null));
    }

    @Test
    public void testToConferenceVoteDtoWithValidEntity() {
        Long memberId = 1L;

        ConferenceVoteEnum vote = ConferenceVoteEnum.SIM;

        ConferenceVote conferenceVote = createConferenceVote();

        ConferenceVoteDto dto = ConferenceVoteMapper.toConferenceVoteDto(conferenceVote);

        assertEquals(vote, dto.getVote());
    }

    @Test
    public void testToConferenceVoteDtoWithNullEntity() {
        assertNull(ConferenceVoteMapper.toConferenceVoteDto(null));
    }

    @Test
    public void testToConferenceVoteResumeDtoWithValidEntity() {
        ConferenceVoteEnum vote = ConferenceVoteEnum.SIM;

        ConferenceVote conferenceVote = createConferenceVote();
        conferenceVote.setVote(vote);

        ConferenceVoteResumeDto dto = ConferenceVoteMapper.toConferenceVoteResumeDto(conferenceVote);

        assertEquals(vote, dto.getVote());
    }

    @Test
    public void testToConferenceVoteResumeDtoWithNullEntity() {
        assertNull(ConferenceVoteMapper.toConferenceVoteResumeDto(null));
    }
}

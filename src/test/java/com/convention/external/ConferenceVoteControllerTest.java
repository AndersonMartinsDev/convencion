package com.convention.external;

import com.convention.application.dto.vote.ConferenceVoteDto;
import com.convention.application.dto.vote.ConferenceVoteResumeDto;
import com.convention.domain.services.ConferenceVoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.convention.mocks.ConferenceVoteMock.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ConferenceVoteControllerTest {

    @Mock
    private ConferenceVoteService conferenceVoteService;

    @InjectMocks
    private ConferenceVoteController conferenceVoteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAMemberVote() {
        ConferenceVoteDto conferenceVoteDto = createConferenceVoteDto();
        ConferenceVoteResumeDto expectedDto = createConferenceVoteResumeDto();

        when(conferenceVoteService.insert(any(ConferenceVoteDto.class))).thenReturn(createConferenceVote());
        conferenceVoteController.getAMemberVote(conferenceVoteDto);
        verify(conferenceVoteService, times(1)).insert(any(ConferenceVoteDto.class));
    }

    @Test
    public void testDeleteVote() {
        Long voteId = 1L;
        conferenceVoteController.deleteVote(voteId);
        verify(conferenceVoteService, times(1)).delete(voteId);
    }


}

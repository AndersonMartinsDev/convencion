package com.convention.domain.services.impl;

import com.convention.application.dto.vote.ConferenceVoteDto;
import com.convention.domain.entities.ConferenceVote;
import com.convention.domain.entities.Member;
import com.convention.domain.entities.enums.StatusDocumentsEnum;
import com.convention.domain.exceptions.ConvencionException;
import com.convention.domain.repositories.ConferenceVoteRepository;
import com.convention.domain.repositories.MemberRepository;
import com.convention.domain.services.ConferenceVoteService;
import com.convention.domain.services.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.convention.application.mapper.ConferenceVoteMapper.toConferenceVote;
import static com.convention.application.mapper.ConferenceVoteMapper.toConferenceVoteDto;
import static com.convention.mocks.ConferenceVoteMock.createConferenceVote;
import static com.convention.mocks.ConferenceVoteMock.createConferenceVoteDto;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ConferenceVoteServiceImplTest {

    @Mock
    private ScheduleService scheduleService;

    @Mock
    private ConferenceVoteRepository repository;

    private ConferenceVoteService conferenceVoteService;
    @Mock
    private CpfCheckServiceFutureImpl client;
    @Mock
    private MemberRepository memberRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        conferenceVoteService = new ConferenceVoteServiceImpl(scheduleService, repository, client, memberRepository);
        when(client.verifyMember(anyString())).thenReturn(CompletableFuture.supplyAsync(()->Map.of("status",StatusDocumentsEnum.ABLE_TO_VOTE)));

    }

    @Test
    public void testInsertValidVote() throws ConvencionException {
        ConferenceVoteDto conferenceVote = createConferenceVoteDto();
        var document = "3204092834";

        when(scheduleService.verifyIfCanApplyVote(conferenceVote.getScheduleId())).thenReturn(false);
        when(repository.exists(any(Example.class))).thenReturn(false); // No existing vote
        when(repository.save(any(ConferenceVote.class))).thenReturn(toConferenceVote(conferenceVote));
        when(memberRepository.findByDocument(anyString())).thenReturn(Member.builder().document(document).build());

        conferenceVoteService.insert(conferenceVote);

        verify(repository, times(1)).save(any(ConferenceVote.class));
    }

    @Test
    public void testInsertExistingVote() throws ConvencionException {
        var document = "3204092834";
        ConferenceVote conferenceVote = createConferenceVote();

        when(scheduleService.verifyIfCanApplyVote(conferenceVote.getScheduleId())).thenReturn(true);
        when(repository.exists(any(Example.class))).thenReturn(true); // Existing vote

        var conferenceVoteDto = toConferenceVoteDto(conferenceVote);
        conferenceVoteDto.setDocument(document);
        assertThrows(ConvencionException.class, () -> conferenceVoteService.insert(conferenceVoteDto));
        verify(repository, times(0)).save(conferenceVote); // Save shouldn't be called
    }

    @Test
    public void testInsertScheduleVotingClosed() throws ConvencionException {
        var document = "3204092834";
        ConferenceVote conferenceVote = createConferenceVote();
        when(repository.exists(any(Example.class))).thenReturn(false);
        when(scheduleService.verifyIfCanApplyVote(conferenceVote.getScheduleId())).thenReturn(true); // Voting closed
        var conferenceVoteDto = toConferenceVoteDto(conferenceVote);
        conferenceVoteDto.setDocument(document);
        assertThrows(ConvencionException.class, () -> spy(conferenceVoteService).insert(conferenceVoteDto));
        verify(repository, times(0)).save(conferenceVote); // Save shouldn't be called
        verify(repository, times(0)).exists(any(Example.class)); // Shouldn't check for existing vote
    }

    @Test
    public void testDeleteVote() {
        Long voteId = 1L;

        conferenceVoteService.delete(voteId);

        verify(repository, times(1)).deleteById(voteId);
    }

}

package com.convention.domain.services.impl;

import com.convention.domain.entities.Schedule;
import com.convention.domain.repositories.MemberRepository;
import com.convention.domain.repositories.ScheduleRepository;
import com.convention.domain.services.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScheduleServiceImplTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private MemberRepository memberRepository; // Not used in this test, but might be needed in others

//    @Mock
//    private ExternalApiService externalApiService; // Mocked external API service

    private ScheduleService scheduleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        scheduleService = new ScheduleServiceImpl(scheduleRepository, memberRepository); // Create service with mocked dependencies
    }

    @Test
    public void testInsertSchedule() throws Exception {
        Schedule schedule = new Schedule();
        Schedule savedSchedule = new Schedule(); // Saved schedule with potentially different ID

        when(scheduleRepository.save(schedule)).thenReturn(savedSchedule);

        Schedule returnedSchedule = scheduleService.insert(schedule);

        assertEquals(savedSchedule, returnedSchedule);
        verify(scheduleRepository, times(1)).save(schedule);
    }

    @Test
    public void testVerifyIfCanApplyVoteBeforeExecution() throws Exception {
        Long scheduleId = 1L;
        Schedule schedule = new Schedule();
        schedule.setDateTimeExecution(LocalDateTime.now().plusHours(1)); // Execution in the future
        schedule.setTimeInMinutes(30);

        when(scheduleRepository.getById(scheduleId)).thenReturn(schedule);
        scheduleService.verifyIfCanApplyVote(scheduleId);

        verify(scheduleRepository, times(1)).getById(scheduleId);
        //verify(externalApiService, times(0)).callExternalApi(any()); // No external API call here
    }

    @Test
    public void testVerifyIfCanApplyVoteAfterExecution() throws Exception {
        Long scheduleId = 1L;
        Schedule schedule = new Schedule();
        schedule.setDateTimeExecution(LocalDateTime.now().minusHours(1)); // Execution in the past
        schedule.setTimeInMinutes(30);

        when(scheduleRepository.getById(scheduleId)).thenReturn(schedule);

        scheduleService.verifyIfCanApplyVote(scheduleId);

        verify(scheduleRepository, times(1)).getById(scheduleId);
        //verify(externalApiService, times(0)).callExternalApi(any()); // No external API call here
    }

    @Test
    public void testVerifyIfCanApplyVoteMissingExecutionTime() throws Exception {
        Long scheduleId = 1L;
        Schedule schedule = new Schedule();
        schedule.setTimeInMinutes(30);

        when(scheduleRepository.getById(scheduleId)).thenReturn(schedule);

        scheduleService.verifyIfCanApplyVote(scheduleId);

        verify(scheduleRepository, times(1)).getById(scheduleId);
        //verify(externalApiService, times(0)).callExternalApi(any()); // No external API call here
    }

    @Test
    public void testVerifyIfCanApplyVoteMissingVotingTime() throws Exception {
        Long scheduleId = 1L;
        Schedule schedule = new Schedule();
        schedule.setDateTimeExecution(LocalDateTime.now().plusHours(1)); // Execution in the future

        when(scheduleRepository.getById(scheduleId)).thenReturn(schedule);

        boolean canApplyVote = scheduleService.verifyIfCanApplyVote(scheduleId);

        assertTrue(canApplyVote);
        verify(scheduleRepository, times(1)).getById(scheduleId);
    }
}

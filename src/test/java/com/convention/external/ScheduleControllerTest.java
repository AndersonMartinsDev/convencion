package com.convention.external;

import com.convention.application.dto.schedule.ScheduleDto;
import com.convention.application.dto.schedule.ScheduleResumeDto;
import com.convention.application.dto.schedule.ScheduleResumoDto;
import com.convention.commons.pagination.ListPagination;
import com.convention.domain.entities.Schedule;
import com.convention.domain.services.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.convention.application.mapper.ScheduleMapper.toSchedule;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController; // Mock the controller itself

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertSchedule() {
        ScheduleDto scheduleDto = createScheduleDto();
        ScheduleDto expectedDto = createScheduleDto();

        when(scheduleService.insert(any(Schedule.class))).thenReturn(toSchedule(expectedDto));
        ScheduleDto actualDto = spy(scheduleController).insert(scheduleDto);

        verify(scheduleService, times(1)).insert(any(Schedule.class));
    }

    @Test
    public void testUpdateStartScheduleWithTime() {
        Long scheduleId = 1L;
        Integer timeInMinutes = 30;
        Schedule expectedDto = createSchedule();
        expectedDto.setId(scheduleId);
        expectedDto.setTimeInMinutes(timeInMinutes);
        expectedDto.setDateTimeExecution(LocalDateTime.now());

        when(scheduleService.setInitTimeVote(any(Schedule.class))).thenReturn(expectedDto);

        scheduleController.update(timeInMinutes, scheduleId);

        verify(scheduleService, times(1)).setInitTimeVote(any(Schedule.class));
    }

    @Test
    public void testUpdateStartScheduleWithoutTime() {
        Long scheduleId = 1L;
        Schedule expectedDto = createSchedule();
        expectedDto.setId(scheduleId);
        expectedDto.setDateTimeExecution(LocalDateTime.now());
        expectedDto.setTimeInMinutes(1);

        when(scheduleService.setInitTimeVote(any(Schedule.class))).thenReturn(expectedDto);

        spy(scheduleController).update(null, scheduleId);
        verify(scheduleService, times(1)).setInitTimeVote(any(Schedule.class));
    }

    @Test
    public void testListAllSchedules() {
        int pageIndex = 0;
        int pageSize = 10;
        List<Schedule> content = Collections.singletonList(createSchedule());
        ListPagination<Schedule> expectedPagination = new ListPagination<>(content, 1L, pageIndex);

        when(scheduleService.listAllRegisters(pageIndex, pageSize)).thenReturn(expectedPagination);

        ListPagination<ScheduleResumeDto> actualPagination = scheduleController.listAllSchedules(pageIndex, pageSize);
        assertNotNull(actualPagination);
        verify(scheduleService, times(1)).listAllRegisters(pageIndex, pageSize);
    }

    @Test
    public void testGetResultForScheduled() {
        Long scheduledId = 1L;
        Map<String, Long> expectedResult = new HashMap<>();

        when(scheduleService.getResultForSchedledId(scheduledId)).thenReturn(expectedResult);

        Map<String, Long> actualResult = scheduleController.getResultForScheduled(scheduledId);

        assertEquals(expectedResult, actualResult);
        verify(scheduleService, times(1)).getResultForSchedledId(scheduledId);
    }

    private ScheduleDto createScheduleDto() {
        return ScheduleDto.builder()
                .name("Schedule description")
                .theme("Schedule theme")
                .build();
    }

    private ScheduleResumoDto createScheduleResumoDto() {
        return ScheduleResumoDto.builder()
                .name("Schedule description")
                .theme("Schedule theme")
                .build();
    }

    private Schedule createSchedule() {
        return Schedule.builder()
                .name("Schedule description")
                .theme("Schedule theme")
                .build();
    }
}

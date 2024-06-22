package com.convention.application.mapper;

import com.convention.application.dto.schedule.ScheduleDto;
import com.convention.application.dto.schedule.ScheduleResumeDto;
import com.convention.application.dto.schedule.ScheduleResumoDto;
import com.convention.application.dto.schedule.ScheduleUpdateDto;
import com.convention.domain.entities.Schedule;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.convention.mocks.ScheduleMock.createNewSchedule;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ScheduleMapperTest {

    @Test
    public void testToScheduleWithScheduleDto() {
        String name = "Test Schedule";
        String theme = "A test theme";

        ScheduleDto dto = ScheduleDto.builder()
                .name(name)
                .theme(theme)
                .build();

        Schedule schedule = ScheduleMapper.toSchedule(dto);

        assertEquals(name, schedule.getName());
        assertEquals(theme, schedule.getTheme());
    }

    @Test
    public void testToScheduleWithScheduleDtoNull() {
        assertNull(ScheduleMapper.toSchedule((ScheduleDto) null));
    }

    @Test
    public void testToScheduleWithScheduleUpdateDto() {
        Long id = 1L;
        int timeInMinutes = 60;

        ScheduleUpdateDto updateDto = ScheduleUpdateDto.builder()
                .id(id)
                .timeInMinutes(timeInMinutes)
                .build();

        Schedule schedule = ScheduleMapper.toSchedule(updateDto);

        assertEquals(id, schedule.getId());
        assertEquals(timeInMinutes, schedule.getTimeInMinutes());
    }

    @Test
    public void testToScheduleWithScheduleUpdateDtoNull() {
        assertNull(ScheduleMapper.toSchedule((ScheduleDto) null));
    }

    @Test
    public void testToScheduleDtoWithSchedule() {
        String name = "Test Schedule";
        String theme = "A test theme";

        Schedule schedule =createNewSchedule(name,theme,null,null);

        ScheduleDto dto = ScheduleMapper.toScheduleDto(schedule);

        assertEquals(name, dto.getName());
        assertEquals(theme, dto.getTheme());
    }

    @Test
    public void testToScheduleDtoWithScheduleNull() {
        assertNull(ScheduleMapper.toScheduleDto(null));
    }

    @Test
    public void testToScheduleResumoDtoWithSchedule() {
        String name = "Test Schedule";
        String theme = "A test theme";
        LocalDateTime dateTimeExecution = LocalDateTime.now();
        int timeInMinutes = 30;

        Schedule schedule = createNewSchedule(name,theme,dateTimeExecution,timeInMinutes);

        ScheduleResumoDto dto = ScheduleMapper.toScheduleResumoDto(schedule);

        assertEquals(name, dto.getName());
        assertEquals(theme, dto.getTheme());
        assertEquals(dateTimeExecution.plusMinutes(timeInMinutes), dto.getEndExecution());
    }

    @Test
    public void testToScheduleResumoDtoWithScheduleNull() {
        assertNull(ScheduleMapper.toScheduleResumoDto(null));
    }

    @Test
    public void testToListScheduleDtoWithList() {
        List<Schedule> schedules = new ArrayList<>();

        schedules.add(createNewSchedule("Schedule 1","Theme 1",null,null));
        schedules.add(createNewSchedule("Schedule 2","Theme 2",null,null));

        List<ScheduleResumeDto> dtoList = ScheduleMapper.toListScheduleDto(schedules);

        assertEquals(2, dtoList.size());
        assertEquals(schedules.get(0).getId(), dtoList.get(0).getId());
        assertEquals(schedules.get(0).getName(), dtoList.get(0).getName());
        assertEquals(schedules.get(1).getId(), dtoList.get(1).getId());
        assertEquals(schedules.get(1).getName(), dtoList.get(1).getName());
    }

    @Test
    public void testToListScheduleDtoWithNullList() {
        assertNull(ScheduleMapper.toListScheduleDto(null));
    }

    @Test
    public void testToListScheduleDtoWithEmptyList() {
        assertEquals(Collections.emptyList(), ScheduleMapper.toListScheduleDto(Collections.emptyList()));
    }

}
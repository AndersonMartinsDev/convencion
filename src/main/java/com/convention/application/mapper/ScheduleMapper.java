package com.convention.application.mapper;

import com.convention.application.dto.schedule.ScheduleDto;
import com.convention.application.dto.schedule.ScheduleResumeDto;
import com.convention.application.dto.schedule.ScheduleResumoDto;
import com.convention.application.dto.schedule.ScheduleUpdateDto;
import com.convention.domain.entities.Schedule;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ScheduleMapper {
    public static Schedule toSchedule(ScheduleDto scheduleDto) {
        if(Objects.isNull(scheduleDto)){
            return null;
        }
        return Schedule.builder()
                .name(scheduleDto.getName())
                .theme(scheduleDto.getTheme())
                .build();
    }

    public static Schedule toSchedule(ScheduleUpdateDto updateDto) {
        if(Objects.isNull(updateDto)){
            return null;
        }
        return Schedule.builder()
                .id(updateDto.getId())
                .timeInMinutes(updateDto.getTimeInMinutes())
                .build();
    }

    public static ScheduleDto toScheduleDto(Schedule entity) {
        if(Objects.isNull(entity)){
            return null;
        }
        return ScheduleDto.builder()
                .name(entity.getName())
                .theme(entity.getTheme())
                .build();
    }

    public static ScheduleResumoDto toScheduleResumoDto(Schedule entity) {
        if(Objects.isNull(entity)){
            return null;
        }
        return ScheduleResumoDto.builder()
                .name(entity.getName())
                .theme(entity.getTheme())
                .endExecution(entity
                        .getDateTimeExecution()
                        .plusMinutes(entity
                                .getTimeInMinutes()))
                .build();
    }



    public static List<ScheduleResumeDto> toListScheduleDto(List<Schedule> schedules) {
        return schedules
                .stream()
                .map(map -> ScheduleResumeDto.builder()
                        .id(map.getId())
                        .name(map.getName())
                        .build()
                ).toList();
    }
    public static Collection<ScheduleResumeDto> toListScheduleDto(Collection<Schedule> schedules) {
        return schedules
                .stream()
                .map(map -> ScheduleResumeDto.builder()
                        .id(map.getId())
                        .name(map.getName())
                        .build()
                ).toList();
    }
}

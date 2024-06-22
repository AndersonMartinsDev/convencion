package com.convention.mocks;

import com.convention.domain.entities.Schedule;

import java.time.LocalDateTime;

public class ScheduleMock {

    public static Schedule createNewSchedule(String name,String theme, LocalDateTime dateTime,Integer timeInMinutes){
        return Schedule
                .builder()
                .theme(theme)
                .name(name)
                .dateTimeExecution(dateTime)
                .timeInMinutes(timeInMinutes)
                .build();
    }
}

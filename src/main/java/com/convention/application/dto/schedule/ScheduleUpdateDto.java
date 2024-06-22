package com.convention.application.dto.schedule;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScheduleUpdateDto {
    private Long id;
    private Integer timeInMinutes;
}

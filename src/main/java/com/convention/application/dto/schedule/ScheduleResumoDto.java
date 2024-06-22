package com.convention.application.dto.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ScheduleResumoDto {
    private String name;
    private String theme;
    private LocalDateTime endExecution;
}

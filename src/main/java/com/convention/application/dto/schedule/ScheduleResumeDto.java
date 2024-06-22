package com.convention.application.dto.schedule;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScheduleResumeDto {
    private Long id;
    private String name;
}

package com.convention.application.dto.ruling;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RulingDto {
    private String theme;
    private Integer orderPriority;
    private Integer timeInMinutes;
}

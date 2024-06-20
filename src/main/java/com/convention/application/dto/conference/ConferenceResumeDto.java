package com.convention.application.dto.conference;

import com.convention.domain.entities.Member;
import com.convention.domain.entities.Ruling;
import com.convention.domain.entities.enums.ConferenceTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class ConferenceResumeDto {
    private Long id;
    private String name;
}

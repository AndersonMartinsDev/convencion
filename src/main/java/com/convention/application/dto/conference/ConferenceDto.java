package com.convention.application.dto.conference;

import com.convention.application.dto.ruling.RulingDto;
import com.convention.domain.entities.Member;
import com.convention.domain.entities.Ruling;
import com.convention.domain.entities.enums.ConferenceTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ConferenceDto {
    private String name;
    private LocalDate dateTimeExecution;
    private String convener;
    private Integer quorum;
    private ConferenceTypeEnum conferenceTypeEnum;
    private List<RulingDto> rulingList;
    private List<MemberDto> membersList;
}

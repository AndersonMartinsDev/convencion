package com.convention.application.dto.vote;

import com.convention.domain.entities.enums.ConferenceVoteEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ConferenceVoteDto {
    private Long memberId;
    private Long scheduleId;
    private ConferenceVoteEnum vote;
}

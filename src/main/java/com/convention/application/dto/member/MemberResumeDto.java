package com.convention.application.dto.member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResumeDto {
    private Long id;
    private String name;
}

package com.convention.application.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDto {

    private Long id;
    private String name;
    private String document;
    private String phone;
    private String email;
}

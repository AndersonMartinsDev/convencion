package com.convention.application.dto.conference;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDto {
    private String name;
    private String document;
    private String phone;
    private String email;
}

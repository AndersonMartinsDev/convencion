package com.convention.application.mapper;

import com.convention.application.dto.conference.MemberDto;
import com.convention.domain.entities.Member;

import java.util.List;

public class MemberMapper {

    public static Member toMember(MemberDto dto){
        return Member
                .builder()
                .name(dto.getName())
                .document(dto.getDocument())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }

    public static List<Member> toListMember(List<MemberDto> list){
        return list.stream().map(MemberMapper::toMember).toList();
    }
}

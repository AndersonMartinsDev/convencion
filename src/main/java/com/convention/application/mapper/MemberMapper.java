package com.convention.application.mapper;

import com.convention.application.dto.member.MemberDto;
import com.convention.application.dto.member.MemberResumeDto;
import com.convention.domain.entities.Member;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MemberMapper {

    public static Member toMember(MemberDto dto){
        if(Objects.isNull(dto)){
            return null;
        }
        return Member
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .document(dto.getDocument())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }

    public static MemberResumeDto toMemberDto(Member entity){
        if(Objects.isNull(entity)){
            return null;
        }
        return MemberResumeDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static List<Member> toListMember(List<MemberDto> list){
        if(Objects.isNull(list) || list.isEmpty()){
            return null;
        }
        return list.stream().map(MemberMapper::toMember).toList();
    }

    public static List<MemberResumeDto> toListMemberDto(List<Member> list){
        if(Objects.isNull(list) || list.isEmpty()){
            return null;
        }
        return list.stream().map(MemberMapper::toMemberDto).toList();
    }

    public static Collection<MemberResumeDto> toCollectionMemberDto(Collection<Member> list){
        if(Objects.isNull(list) || list.isEmpty()){
            return null;
        }
        return list.stream().map(MemberMapper::toMemberDto).collect(Collectors.toList());
    }
}

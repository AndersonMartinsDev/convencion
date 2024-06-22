package com.convention.application.mapper;

import com.convention.application.dto.member.MemberDto;
import com.convention.application.dto.member.MemberResumeDto;
import com.convention.domain.entities.Member;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MemberMapperTest {

    @Test
    public void testToMemberWithValidDto() {
        Long id = 1L;
        String name = "John Doe";
        String document = "12345678900";
        String email = "john.doe@email.com";
        String phone = "(11) 98765-4321";

        MemberDto dto = MemberDto.builder()
                .id(id)
                .name(name)
                .document(document)
                .email(email)
                .phone(phone)
                .build();

        Member member = MemberMapper.toMember(dto);

        assertEquals(id, member.getId());
        assertEquals(name, member.getName());
        assertEquals(document, member.getDocument());
        assertEquals(email, member.getEmail());
        assertEquals(phone, member.getPhone());
    }

    @Test
    public void testToMemberWithNullDto() {
        assertNull(MemberMapper.toMember(null));
    }

    @Test
    public void testToMemberDtoWithValidEntity() {
        Long id = 1L;
        String name = "John Doe";

        Member member = new Member(id, name, null, null, null);

        MemberResumeDto dto = MemberMapper.toMemberDto(member);

        assertEquals(id, dto.getId());
        assertEquals(name, dto.getName());
    }

    @Test
    public void testToMemberDtoWithNullEntity() {
        assertNull(MemberMapper.toMemberDto(null));
    }

    @Test
    public void testToListMemberWithValidList() {
        List<MemberDto> dtoList = new ArrayList<>();
        dtoList.add(MemberDto.builder().id(1L).name("Member 1").build());
        dtoList.add(MemberDto.builder().id(2L).name("Member 2").build());

        List<Member> memberList = MemberMapper.toListMember(dtoList);

        assertEquals(2, memberList.size());
        assertEquals(1L, memberList.get(0).getId());
        assertEquals("Member 1", memberList.get(0).getName());
        assertEquals(2L, memberList.get(1).getId());
        assertEquals("Member 2", memberList.get(1).getName());
    }

    @Test
    public void testToListMemberWithNullList() {
        assertNull(MemberMapper.toListMember(null));
    }

    @Test
    public void testToListMemberWithEmptyList() {
        assertNull(MemberMapper.toListMember(Collections.emptyList()));
    }

    @Test
    public void testToListMemberDtoWithValidList() {
        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member(1L, "Member 1", null, null, null));
        memberList.add(new Member(2L, "Member 2", null, null, null));

        List<MemberResumeDto> dtolist = MemberMapper.toListMemberDto(memberList);

        assertEquals(2, dtolist.size());
        assertEquals(1L, dtolist.get(0).getId());
        assertEquals("Member 1", dtolist.get(0).getName());
        assertEquals(2L, dtolist.get(1).getId());
        assertEquals("Member 2", dtolist.get(1).getName());
    }

    @Test
    public void testToListMemberDtoWithNullList() {
        assertNull(MemberMapper.toListMemberDto(null));
    }

    @Test
    public void testToListMemberDtoWithEmptyList() {
        assertNull(MemberMapper.toListMemberDto(Collections.emptyList()));
    }
}

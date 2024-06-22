package com.convention.domain.services.impl;

import com.convention.commons.pagination.ListPagination;
import com.convention.domain.entities.Member;
import com.convention.domain.repositories.MemberRepository;
import com.convention.domain.services.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    private MemberService memberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        memberService = new MemberServiceImpl(memberRepository); // Create service with mocked repository
    }

    @Test
    public void testNewMember() throws Exception {
        Member member = new Member();
        Member savedMember = new Member(); // Saved member with potentially different ID

        when(memberRepository.save(member)).thenReturn(savedMember);

        Member returnedMember = memberService.newMember(member);

        assertEquals(savedMember, returnedMember);
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    public void testNewListMember() throws Exception {
        List<Member> members = new ArrayList<>();
        List<Member> savedMembers = Collections.singletonList(new Member()); // Saved list with potentially different objects

        when(memberRepository.saveAll(members)).thenReturn(savedMembers);

        List<Member> returnedMembers = memberService.newListMember(members);

        assertEquals(savedMembers, returnedMembers);
        verify(memberRepository, times(1)).saveAll(members);
    }

    @Test
    public void testDeleteMember() throws Exception {
        Long memberId = 1L;
        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("status", "Removido Com Sucesso!");

        memberService.deleteMember(memberId);

        verify(memberRepository, times(1)).deleteById(memberId);
    }

    @Test
    public void testUpdateMember() throws Exception {
        Long memberId = 1L;
        Member member = new Member();
        member.setId(memberId);
        member.setName("Updated Name");

        Member oldMember = new Member(); // Existing member retrieved from repository
        oldMember.setId(memberId);
        oldMember.setName("Old Name");

        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("status", "Registro Atualizado!");

        when(memberRepository.getById(memberId)).thenReturn(oldMember);

        Map<String, String> actualResponse = memberService.updateMember(member);

        assertEquals(expectedResponse, actualResponse);
        verify(memberRepository, times(1)).getById(memberId);
        verify(memberRepository, times(1)).save(oldMember); // Verify updated member is saved
    }

    @Test
    public void testPaginationListMember() throws Exception {
        int pageIndex = 0;
        int pageSize = 10;
        List<Member> content = Collections.singletonList(new Member());
        ListPagination<Member> expectedPagination = new ListPagination<>(content, 1L, pageIndex);

        when(memberRepository.findPagination(pageIndex, pageSize)).thenReturn(expectedPagination);

        ListPagination<Member> actualPagination = memberService.paginationListMember(pageIndex, pageSize);

        assertEquals(expectedPagination, actualPagination);
        verify(memberRepository, times(1)).findPagination(pageIndex, pageSize);
    }
}

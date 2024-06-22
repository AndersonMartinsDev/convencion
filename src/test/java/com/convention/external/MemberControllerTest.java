package com.convention.external;

import com.convention.application.dto.member.MemberDto;
import com.convention.application.dto.member.MemberResumeDto;
import com.convention.commons.pagination.ListPagination;
import com.convention.domain.entities.Member;
import com.convention.domain.services.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.convention.application.mapper.MemberMapper.toListMember;
import static org.mockito.Mockito.*;

public class MemberControllerTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewMember() {
        Member expectedDto = createToMember();

        when(memberService.newMember(any(Member.class))).thenReturn(expectedDto);

        memberController.newMember(createToMemberDto());

        verify(memberService, times(1)).newMember(any(Member.class));
    }

    @Test
    public void testNewListMember() {
        List<MemberDto> expectedDtos = Collections.singletonList(createToMemberDto());

        when(memberService.newListMember(anyList())).thenReturn(toListMember(expectedDtos));

        memberController.newListMember(expectedDtos);

        verify(memberService, times(1)).newListMember(anyList());
    }

    @Test
    public void testUpdateMember() {
        MemberDto memberDto = createToMemberDto();
        Map<String, String> expectedResponse = new HashMap<>();

        when(memberService.updateMember(any(Member.class))).thenReturn(expectedResponse);

        Map<String, String> actualResponse = memberController.updateMember(memberDto);

        verify(memberService, times(1)).updateMember(any(Member.class));
    }

    @Test
    public void testDeleteMember() {
        Long memberId = 1L;
        Map<String, String> expectedResponse = new HashMap<>();

        when(memberService.deleteMember(memberId)).thenReturn(expectedResponse);

        Map<String, String> actualResponse = memberController.deleteMember(memberId);

        verify(memberService, times(1)).deleteMember(memberId);
    }

    @Test
    public void testListMember() {
        int pageIndex = 0;
        int pageSize = 10;
        List<Member> content = Collections.singletonList(createToMember());
        ListPagination<Member> expectedPagination = new ListPagination<>(content, 1L, pageIndex);

        when(memberService.paginationListMember(pageIndex, pageSize)).thenReturn(expectedPagination);

        ListPagination<MemberResumeDto> actualPagination = memberController.listMember(pageIndex, pageSize);

        verify(memberService, times(1)).paginationListMember(pageIndex, pageSize);
    }

    private Member createToMember(){
        return Member
                .builder()
                .id(1l)
                .phone("394820384")
                .name("lksdjflksdj")
                .document("32490823409")
                .build();
    }

    private MemberDto createToMemberDto(){
        return MemberDto
                .builder()
                .id(1l)
                .phone("394820384")
                .name("lksdjflksdj")
                .document("32490823409")
                .build();
    }
    private MemberResumeDto createToMemberResumoDto(){
        return MemberResumeDto
                .builder()
                .id(1l)
                .name("lksdjflksdj")
                .build();
    }
}

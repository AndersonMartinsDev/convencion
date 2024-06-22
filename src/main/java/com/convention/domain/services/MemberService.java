package com.convention.domain.services;

import com.convention.application.dto.member.MemberDto;
import com.convention.commons.pagination.ListPagination;
import com.convention.domain.entities.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {
    Member newMember(Member entity);
    List<Member> newListMember(List<Member> entity);
    Map<String, String> deleteMember(Long memberId);

    Map<String, String> updateMember(Member member);

    ListPagination<Member> paginationListMember(Integer pageIndex,Integer pageQuantity) ;
}

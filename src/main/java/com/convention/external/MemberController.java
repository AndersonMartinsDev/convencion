package com.convention.external;

import com.convention.application.dto.member.MemberDto;
import com.convention.application.dto.member.MemberResumeDto;
import com.convention.commons.pagination.ListPagination;
import com.convention.domain.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.convention.application.mapper.MemberMapper.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    public MemberResumeDto newMember(@RequestBody MemberDto conference) {
        return toMemberDto(service.newMember(toMember(conference)));
    }

    @PostMapping(value = "/new-list-members")
    public List<MemberResumeDto> newListMember(@RequestBody List<MemberDto> conference) {
        return toListMemberDto(service.newListMember(toListMember(conference)));
    }

    @PutMapping
    public Map<String, String> updateMember(@RequestBody MemberDto memberDto) {
        return service.updateMember(toMember(memberDto));
    }

    @DeleteMapping
    public Map<String, String> deleteMember(@RequestParam("memberId") Long memberId) {
        return service.deleteMember(memberId);
    }

    @GetMapping(value = "list")
    public ListPagination<MemberResumeDto> listMember(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
        var list = service.paginationListMember(pageIndex, pageSize);
        return new ListPagination<MemberResumeDto>(toCollectionMemberDto(list.getContent()),list.getTotal(), list.getPage());
    }

}

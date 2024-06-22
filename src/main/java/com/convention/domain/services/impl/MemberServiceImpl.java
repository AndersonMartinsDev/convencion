package com.convention.domain.services.impl;

import com.convention.commons.pagination.ListPagination;
import com.convention.domain.entities.Member;
import com.convention.domain.repositories.MemberRepository;
import com.convention.domain.services.MemberService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {

    public final MemberRepository repository;

    @Autowired
    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    @SneakyThrows
    public Member newMember(Member entity) {
        return repository.save(entity);
    }

    @Override
    @SneakyThrows
    public List<Member> newListMember(List<Member> entity) {
        return repository.saveAll(entity);
    }

    @Override
    @SneakyThrows
    public Map<String, String> deleteMember(Long memberId) {
        repository.deleteById(memberId);
        return Map.of("status","Removido Com Sucesso!");
    }

    @Override
    @SneakyThrows
    public Map<String, String> updateMember(Member member) {
        var oldMember = repository.getById(member.getId());
        oldMember.setDocument(member.getDocument());
        oldMember.setEmail(member.getEmail());
        oldMember.setName(member.getName());
        oldMember.setPhone(member.getPhone());
        repository.save(oldMember);
        return Map.of("status","Registro Atualizado!");
    }

    @Override
    @SneakyThrows
    public ListPagination<Member> paginationListMember(Integer pageIndex,Integer pageQuantity) {
        return repository.findPagination(pageIndex,pageQuantity);
    }
}

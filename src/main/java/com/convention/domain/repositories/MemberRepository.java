package com.convention.domain.repositories;


import com.convention.commons.repository.GenericRepository;
import com.convention.domain.entities.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends GenericRepository<Member, Long> {
    Member findByDocument(String document);
}

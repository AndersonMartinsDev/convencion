package com.convention.domain.repositories;

import com.convention.domain.entities.ConferenceVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceVoteRepository extends JpaRepository<ConferenceVote,Long> {

}

package com.convention.domain.repositories;


import com.convention.commons.repository.GenericRepository;
import com.convention.domain.entities.Schedule;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ScheduleRepository extends GenericRepository<Schedule,Long> {
    //TODO: COLOCAR O WHERE DESSA CONSULTA
    @Query(nativeQuery = true,
            value = "with t1 AS (" +
                    " SELECT \n" +
                    " COUNT(cv.vote) AS countYes, " +
                    " COUNT(cv.member_id) AS totalVotes " +
                    " FROM conference_vote cv) " +
                    "SELECT countYes,countYes - totalVotes AS countNo,totalVotes FROM t1 ")
    Map<String,Long> getResultForVote(@Param("scheduledId")Long scheduledId);
}

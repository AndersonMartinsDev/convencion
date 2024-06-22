package com.convention.domain.repositories;


import com.convention.commons.repository.GenericRepository;
import com.convention.domain.entities.Schedule;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ScheduleRepository extends GenericRepository<Schedule, Long> {
    @Query(nativeQuery = true,
            value = "with t1 AS (" +
                    " SELECT  " +
                    " (SELECT COUNT(cvs.vote)" +
                    "  FROM conference_vote cvs" +
                    "  WHERE cvs.schedule_id = :scheduledId" +
                    "  AND cvs.vote = 'SIM') AS countYes," +
                    " COUNT(cv.member_id) AS totalVotes" +
                    " FROM conference_vote cv" +
                    " WHERE cv.schedule_id = :scheduledId )" +
                    "SELECT countYes,totalVotes - countYes  AS countNo,  totalVotes FROM t1 ")
    Map<String, Long> getResultForVote(@Param("scheduledId") Long scheduledId);
}

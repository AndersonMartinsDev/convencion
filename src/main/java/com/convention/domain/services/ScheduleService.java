package com.convention.domain.services;

import com.convention.commons.pagination.ListPagination;
import com.convention.domain.entities.Schedule;

import java.util.Map;

public interface ScheduleService {
    Schedule insert(Schedule schedule);

    Boolean verifyIfCanApplyVote(Long scheduleId);
    ListPagination<Schedule> listAllRegisters(Integer pageIndex, Integer pageQuantity);

    Schedule setInitTimeVote(Schedule schedule);

    Map<String,Long> getResultForSchedledId(Long scheduledId);
}

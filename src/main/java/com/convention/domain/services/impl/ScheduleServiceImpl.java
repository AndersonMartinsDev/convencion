package com.convention.domain.services.impl;

import com.convention.commons.pagination.ListPagination;
import com.convention.domain.entities.Schedule;
import com.convention.domain.repositories.MemberRepository;
import com.convention.domain.repositories.ScheduleRepository;
import com.convention.domain.services.ScheduleService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    public final ScheduleRepository repository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository repository, MemberRepository memberRepository) {
        this.repository = repository;
    }

    @Override
    @SneakyThrows
    public Schedule insert(Schedule schedule) {
        return repository.save(schedule);
    }

    @Override
    public Boolean verifyIfCanApplyVote(Long scheduleId) {

        //TODO: COLOCAR A CHAMDA DA API
        var schedule = repository.getById(scheduleId);
        var execution = schedule.getDateTimeExecution();
        var timeInMinutes = schedule.getTimeInMinutes();

        return (Objects.isNull(execution) || Objects.isNull(timeInMinutes))
                || execution.plusMinutes(timeInMinutes).isBefore(LocalDateTime.now());
    }

    @Override
    public ListPagination<Schedule> listAllRegisters(Integer pageIndex, Integer pageQuantity) {
        return repository.findPagination(pageIndex,pageQuantity);
    }

    @Override
    public Schedule setInitTimeVote(Schedule schedule) {
        var oldConference = repository.getById(schedule.getId());
        oldConference.setDateTimeExecution(LocalDateTime.now());
        oldConference.setTimeInMinutes(schedule.getTimeInMinutes());
        return repository.save(oldConference);
    }

    @Override
    public Map<String,Long> getResultForSchedledId(Long scheduledId) {
        return repository.getResultForVote(scheduledId);
    }
}

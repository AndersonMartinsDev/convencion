package com.convention.external;

import com.convention.application.dto.schedule.ScheduleDto;
import com.convention.application.dto.schedule.ScheduleResumeDto;
import com.convention.application.dto.schedule.ScheduleResumoDto;
import com.convention.application.dto.schedule.ScheduleUpdateDto;
import com.convention.commons.pagination.ListPagination;
import com.convention.domain.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

import static com.convention.application.mapper.ScheduleMapper.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService service;

    @Autowired
    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @PostMapping
    public ScheduleDto insert(@RequestBody ScheduleDto conference) {
        return toScheduleDto(service.insert(toSchedule(conference)));
    }

    @PutMapping(value = "/start-schedule")
    public ScheduleResumoDto update(@RequestParam(value = "timeInMinutes",required = false) Integer time, @RequestParam("scheduleId") Long id) {
        return toScheduleResumoDto(service
                .setInitTimeVote(
                        toSchedule(ScheduleUpdateDto
                                .builder()
                                .id(id)
                                .timeInMinutes(Objects.isNull(time) ? 1 : time)
                                .build())));
    }

    @GetMapping(value = "/list")
    public ListPagination<ScheduleResumeDto> listAllSchedules(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize) {
        var list = service.listAllRegisters(pageIndex,pageSize);
        return new ListPagination<ScheduleResumeDto>(toListScheduleDto(list.getContent()),list.getTotal(),list.getPage());
    }

    @GetMapping(value="/result")
    public Map<String,Long> getResultForScheduled(@RequestParam("scheduledId")Long scheduledId){
        return service.getResultForSchedledId(scheduledId);
    }
}

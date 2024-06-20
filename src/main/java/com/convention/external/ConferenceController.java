package com.convention.external;

import com.convention.application.dto.conference.ConferenceDto;
import com.convention.application.dto.conference.ConferenceResumeDto;
import com.convention.domain.entities.Conference;
import com.convention.domain.services.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.convention.application.mapper.ConferenceMapper.*;

@RestController
@RequestMapping("/conference")
public class ConferenceController {

    private final ConferenceService service;

    @Autowired
    public ConferenceController(ConferenceService service) {
        this.service = service;
    }

    @PostMapping
    public Conference insert(@RequestBody ConferenceDto conference){
        return service.insert(toConference(conference));
    }

    @GetMapping
    public List<ConferenceResumeDto> list(){
        return toListConferenceDto(service.listAllRegisters());
    }
}

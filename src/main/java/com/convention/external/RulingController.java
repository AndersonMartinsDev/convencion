package com.convention.external;


import com.convention.application.dto.ruling.RulingDto;
import com.convention.domain.services.RulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.convention.application.mapper.RulingMapper.*;

@RestController
@RequestMapping("/ruling")
public class RulingController {
    private final RulingService service;

    @Autowired
    public RulingController(RulingService service) {
        this.service = service;
    }

    @PostMapping
    public RulingDto insert(@RequestBody RulingDto rulingDto){
        var rulingTransfer = toRuling(rulingDto);
        return toRulingDto(service.insert(rulingTransfer));
    }

    @GetMapping
    public List<RulingDto> findAllByConference(@RequestParam("conferenceId")Integer conferenceId){
        return toListRulingDto(service.listAllByConferenceId(conferenceId));
    }

}

package com.convention.external;

import com.convention.application.dto.vote.ConferenceVoteResumeDto;
import com.convention.application.dto.vote.ConferenceVoteDto;
import com.convention.domain.services.ConferenceVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.convention.application.mapper.ConferenceVoteMapper.*;

@RestController
@RequestMapping(value = "/vote")
public class ConferenceVoteController {

    private final ConferenceVoteService service;

    @Autowired
    public ConferenceVoteController(ConferenceVoteService service) {
        this.service = service;
    }

    @PostMapping
    public ConferenceVoteResumeDto getAMemberVote(@RequestBody ConferenceVoteDto conferenceVoteDto){
        //TODO: PERMITIR O USUARIO VOTAR COM O DOCUMENTO
        return toConferenceVoteResumeDto(service.insert(toConferenceVote(conferenceVoteDto)));
    }

    @DeleteMapping(value = "/{index}")
    public void deleteVote(@PathVariable("index")Long index){
        service.delete(index);
    }
}

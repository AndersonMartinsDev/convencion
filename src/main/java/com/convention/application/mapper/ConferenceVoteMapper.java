package com.convention.application.mapper;

import com.convention.application.dto.vote.ConferenceVoteDto;
import com.convention.application.dto.vote.ConferenceVoteResumeDto;
import com.convention.domain.entities.ConferenceVote;

import java.util.Objects;

public class ConferenceVoteMapper {
    public static ConferenceVote toConferenceVote(ConferenceVoteDto dto){

        if(Objects.isNull(dto)){
            return null;
        }
        return ConferenceVote
                .builder()
                .scheduleId(dto.getScheduleId())
                .vote(dto.getVote())
                .build();
    }

    public static ConferenceVoteDto toConferenceVoteDto(ConferenceVote entity){

        if(Objects.isNull(entity)){
            return null;
        }
        return ConferenceVoteDto
                .builder()
                .vote(entity.getVote())
                .build();
    }

    public static ConferenceVoteResumeDto toConferenceVoteResumeDto(ConferenceVote entity){

        if(Objects.isNull(entity)){
            return null;
        }
        return ConferenceVoteResumeDto
                .builder()
                .vote(entity.getVote())
                .build();
    }


}

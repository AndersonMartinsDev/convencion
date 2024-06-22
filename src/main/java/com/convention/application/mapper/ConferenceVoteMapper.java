package com.convention.application.mapper;

import com.convention.application.dto.vote.ConferenceVoteDto;
import com.convention.application.dto.vote.ConferenceVoteResumeDto;
import com.convention.domain.entities.ConferenceVote;
import com.convention.domain.entities.Member;
import com.convention.domain.entities.Schedule;

import java.util.Objects;

public class ConferenceVoteMapper {
    public static ConferenceVote toConferenceVote(ConferenceVoteDto dto){

        if(Objects.isNull(dto)){
            return null;
        }
        return ConferenceVote
                .builder()
                .member(Member.builder().id(dto.getMemberId()).build())
                .schedule(Schedule.builder().id(dto.getScheduleId()).build())
                .vote(dto.getVote())
                .build();
    }

    public static ConferenceVoteDto toConferenceVoteDto(ConferenceVote entity){

        if(Objects.isNull(entity)){
            return null;
        }
        return ConferenceVoteDto
                .builder()
                .memberId(entity.getMember().getId())
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

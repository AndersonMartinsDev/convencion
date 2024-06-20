package com.convention.application.mapper;

import com.convention.application.dto.conference.ConferenceDto;
import com.convention.application.dto.conference.ConferenceResumeDto;
import com.convention.domain.entities.Conference;

import java.util.List;

public class ConferenceMapper {
    public static Conference toConference(ConferenceDto conferenceDto) {
        return Conference.builder()
                .name(conferenceDto.getName())
                .dateTimeExecution(conferenceDto.getDateTimeExecution())
                .convener(conferenceDto.getConvener())
                .quorum(conferenceDto.getQuorum())
                .conferenceTypeEnum(conferenceDto.getConferenceTypeEnum())
                .membersList(MemberMapper.toListMember(conferenceDto.getMembersList()))
                .rulingList(RulingMapper.toListRuling(conferenceDto.getRulingList()))
                .build();
    }

    public static List<ConferenceResumeDto> toListConferenceDto(List<Conference> conferences) {
        return conferences
                .stream()
                .map(map -> ConferenceResumeDto.builder()
                        .id(map.getId())
                        .name(map.getName())
                        .build()
                ).toList();
    }
}

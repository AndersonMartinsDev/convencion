package com.convention.application.mapper;

import com.convention.application.dto.ruling.RulingDto;
import com.convention.domain.entities.Ruling;

import java.util.List;

public class RulingMapper {
    public static Ruling toRuling(RulingDto rulingDto) {
        return Ruling
                .builder()
                .theme(rulingDto.getTheme())
                .timeInMinutes(rulingDto.getTimeInMinutes())
                .orderPriority(rulingDto.getOrderPriority())
                .build();
    }

    public static RulingDto toRulingDto(Ruling rulingDto){
        return RulingDto.builder()
                .theme(rulingDto.getTheme())
                .timeInMinutes(rulingDto.getTimeInMinutes())
                .orderPriority(rulingDto.getOrderPriority())
                .build();
    }

    public static List<Ruling> toListRuling(List<RulingDto> list){
        return list.stream().map(RulingMapper::toRuling).toList();
    }

    public static List<RulingDto> toListRulingDto(List<Ruling> ruling){
        return ruling.stream().map(RulingMapper::toRulingDto).toList();
    }

}

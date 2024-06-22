package com.convention.commons.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ExceptionModel {
    private LocalDateTime timestamp;
    private String message;
    private Integer status;

    private String error;


}

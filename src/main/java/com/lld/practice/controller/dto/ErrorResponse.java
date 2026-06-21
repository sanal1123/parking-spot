package com.lld.practice.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {
    private final String errorCode;
    private final String errorMessage;
}

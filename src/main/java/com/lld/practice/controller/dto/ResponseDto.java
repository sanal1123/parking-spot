package com.lld.practice.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Collection;
import java.util.List;

@Getter
@Builder
public class ResponseDto<T>{
    private Collection<T> data;
    private List<ErrorResponse> errors;
}

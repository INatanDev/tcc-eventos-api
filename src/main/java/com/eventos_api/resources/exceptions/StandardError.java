package com.eventos_api.resources.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
public class StandardError{

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    public String path;
}

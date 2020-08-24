package com.kcc.core;

import lombok.Data;

@Data
public class ResultFieldError {
    private String field;
    private String message;
}

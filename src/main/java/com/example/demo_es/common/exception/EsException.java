package com.example.demo_es.common.exception;

import com.example.demo_es.common.enums.ExceptionEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EsException extends RuntimeException {
    private ExceptionEnums exceptionEnums;
}

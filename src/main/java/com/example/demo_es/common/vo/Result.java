package com.example.demo_es.common.vo;

import lombok.Data;

@Data
public class Result {
    private Integer code;
    private String message;
    private Object date;

    public Result(Integer code, String message, Object date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }
}

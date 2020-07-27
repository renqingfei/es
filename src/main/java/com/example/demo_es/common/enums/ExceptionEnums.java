package com.example.demo_es.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ExceptionEnums {
    ADD_ADDRESS_OK(200,"添加成功"),
    ADD_ADDRESS_ER(400,"添加失败"),
    QUERY_ADDRESS_ES_OK(200,"全量同步到es成功"),
    QUERY_ADDRESS_ES_ER(400,"全量同步到es失败");
    private int code;
    private String msg;
}

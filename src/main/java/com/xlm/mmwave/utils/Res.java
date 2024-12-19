package com.xlm.mmwave.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xlm
 * 2024/12/2 下午5:21
 */
@Data
@AllArgsConstructor
public class Res {
    private Integer code;
    private String msg;
    private Object data;

    public static Res ok(Object data) {
        return new Res(20000, "ok", data);
    }
}

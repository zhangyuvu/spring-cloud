package com.zy.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zy
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CommonResult<T> implements Serializable {
    // 返回给前端的数字码 如404 500
    private Integer code;

    // 返回给前端的信息
    private String message;

    // 传入的实体类
    private T data;

    // 无泛型
    public CommonResult(Integer code, String message){
        this(code, message, null);
    }

}

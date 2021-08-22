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
public class Payment implements Serializable {

    private Long id;
    private String serial;

}

package com.sun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

import java.io.Serializable;

/**
 * Created by pengjikun on 2017/2/15.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    private int id;
    private String name;
    private String birth;
    private String address;
    private int sex;

}

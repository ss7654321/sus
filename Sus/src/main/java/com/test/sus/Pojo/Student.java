package com.test.sus.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private Integer id;
    private String name;
    private String code;
    private String email;
    private String userName;
    private String password;
    private String idCard;
    private Integer type;
}

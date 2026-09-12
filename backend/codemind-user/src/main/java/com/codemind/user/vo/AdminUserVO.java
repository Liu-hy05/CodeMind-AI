package com.codemind.user.vo;


import lombok.Data;


@Data
public class AdminUserVO {


    private Long id;


    private String username;


    private String role;


    private Integer status;


    private String nickname;


    private String email;


    private String phone;

}
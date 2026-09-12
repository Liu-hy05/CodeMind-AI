package com.codemind.user.dto;


import lombok.Data;


@Data
public class UpdateRoleDTO {


    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 新角色
     */
    private String role;

}
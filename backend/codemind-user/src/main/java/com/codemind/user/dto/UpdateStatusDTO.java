package com.codemind.user.dto;


import lombok.Data;


@Data
public class UpdateStatusDTO {


    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 账号状态
     * 1 正常
     * 0 禁用
     */
    private Integer status;

}
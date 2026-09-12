package com.codemind.user.dto;


import jakarta.validation.constraints.Email;
import lombok.Data;


@Data
public class UpdateProfileDTO {


    private String nickname;


    @Email(message = "邮箱格式错误")
    private String email;


    private String phone;

}
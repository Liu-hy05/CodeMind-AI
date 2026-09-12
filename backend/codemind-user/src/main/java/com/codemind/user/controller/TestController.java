package com.codemind.user.controller;


import com.codemind.common.constants.UserConstants;
import com.codemind.common.result.Result;
import com.codemind.user.annotation.RequireRole;
import com.codemind.user.context.UserContext;
import com.codemind.user.service.UserService;
import com.codemind.user.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private UserService userService;

    @GetMapping("/user")
    public Result<Long> getCurrentUser(){

        Long userId =
                UserContext.getUserId();


        return Result.success(userId);
    }

    @GetMapping("/role")
    public Result<String> getCurrentRole() {

        String role = UserContext.getRole();

        return Result.success(role);
    }

    @RequireRole(UserConstants.ADMIN)
    @GetMapping("/admin")
    public Result<String> adminOnly(){

        return Result.success(
                "管理员访问成功"
        );
    }

    @GetMapping("/info")
    public Result<UserVO> info(){

        return Result.success(
                userService.getCurrentUser()
        );

    }
}
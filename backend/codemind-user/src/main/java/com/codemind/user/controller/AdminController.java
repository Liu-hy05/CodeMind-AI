package com.codemind.user.controller;


import com.codemind.common.constants.UserConstants;
import com.codemind.common.result.Result;
import com.codemind.user.annotation.RequireRole;
import com.codemind.user.dto.UpdateRoleDTO;
import com.codemind.user.dto.UpdateStatusDTO;
import com.codemind.user.service.UserService;
import com.codemind.user.vo.AdminUserVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {


    @Resource
    private UserService userService;


    @RequireRole(UserConstants.ADMIN)
    @GetMapping("/users")
    public Result<List<AdminUserVO>> getUsers(){

        return Result.success(
                userService.getUserList()
        );

    }

    @GetMapping("/user/{id}")
    @RequireRole(UserConstants.ADMIN)
    public Result<AdminUserVO> getUserDetail(
            @PathVariable Long id
    ){

        return Result.success(
                userService.getUserDetail(id)
        );
    }

    @PutMapping("/user/role")
    @RequireRole(UserConstants.ADMIN)
    public Result<Void> updateRole(
            @RequestBody UpdateRoleDTO updateRoleDTO
    ){

        userService.updateRole(updateRoleDTO);

        return Result.success();

    }

    @PutMapping("/user/status")
    @RequireRole(UserConstants.ADMIN)
    public Result<Void> updateStatus(
            @RequestBody UpdateStatusDTO updateStatusDTO
    ){

        userService.updateStatus(updateStatusDTO);

        return Result.success();

    }
}
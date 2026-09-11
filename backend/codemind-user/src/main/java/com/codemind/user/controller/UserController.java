package com.codemind.user.controller;

import com.codemind.common.result.Result;
import com.codemind.user.dto.LoginDTO;
import com.codemind.user.dto.RegisterDTO;
import com.codemind.user.service.UserService;
import com.codemind.user.vo.LoginVO;
import com.codemind.user.vo.UserVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Result<LoginVO> login(
            @Valid @RequestBody LoginDTO loginDTO) {

        LoginVO loginVO = userService.login(loginDTO);

        return Result.success(loginVO);
    }

    @PostMapping("/register")
    public Result<UserVO> register(
            @Valid @RequestBody RegisterDTO registerDTO) {

        UserVO userVO = userService.register(registerDTO);

        return Result.success(userVO);
    }

    @GetMapping("/{username}")
    public Result<UserVO> getUserByUsername(
            @PathVariable String username) {

        UserVO userVO = userService.getUserByUsername(username);

        return Result.success(userVO);
    }
}
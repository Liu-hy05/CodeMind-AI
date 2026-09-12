package com.codemind.user.controller;

import com.codemind.common.result.Result;
import com.codemind.user.dto.LoginDTO;
import com.codemind.user.dto.RegisterDTO;
import com.codemind.user.dto.UpdatePasswordDTO;
import com.codemind.user.dto.UpdateProfileDTO;
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
            @RequestBody LoginDTO loginDTO
    ){

        return Result.success(
                userService.login(loginDTO)
        );
    }

    @PostMapping("/register")
    public Result<UserVO> register(
            @Valid @RequestBody RegisterDTO registerDTO) {

        UserVO userVO = userService.register(registerDTO);

        return Result.success(userVO);
    }

    @GetMapping("/info")
    public Result<UserVO> info(){

        return Result.success(
                userService.getCurrentUser()
        );
    }

    @GetMapping("/detail/{username}")
    public Result<UserVO> getUserByUsername(
            @PathVariable("username") String username) {

        UserVO userVO =
                userService.getUserByUsername(username);

        return Result.success(userVO);
    }

    @PutMapping("/password")
    public Result<Void> updatePassword(
            @Valid @RequestBody UpdatePasswordDTO updatePasswordDTO
    ){

        userService.updatePassword(updatePasswordDTO);

        return Result.success();
    }

    @PutMapping("/profile")
    public Result<Void> updateProfile(
            @Valid @RequestBody UpdateProfileDTO updateProfileDTO
    ){

        userService.updateProfile(updateProfileDTO);

        return Result.success();

    }

    @DeleteMapping("/account")
    public Result<Void> disableAccount(){

        userService.disableAccount();

        return Result.success();

    }
}
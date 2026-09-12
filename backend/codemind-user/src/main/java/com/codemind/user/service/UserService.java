package com.codemind.user.service;

import com.codemind.user.dto.*;
import com.codemind.user.vo.AdminUserVO;
import com.codemind.user.vo.LoginVO;
import com.codemind.user.vo.UserVO;

import java.util.List;

public interface UserService {


    UserVO register(RegisterDTO registerDTO);


    LoginVO login(LoginDTO loginDTO);


    UserVO getUserByUsername(String username);


    UserVO getCurrentUser();

    void updatePassword(
            UpdatePasswordDTO updatePasswordDTO
    );

    void updateProfile(
            UpdateProfileDTO updateProfileDTO
    );

    void disableAccount();

    List<AdminUserVO> getUserList();

    AdminUserVO getUserDetail(Long id);

    void updateRole(
            UpdateRoleDTO updateRoleDTO
    );

    void updateStatus(
            UpdateStatusDTO updateStatusDTO
    );
}
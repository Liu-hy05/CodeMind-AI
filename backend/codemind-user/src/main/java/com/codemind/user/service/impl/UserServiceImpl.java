package com.codemind.user.service.impl;

import com.codemind.common.constants.UserConstants;
import com.codemind.common.exception.BusinessException;
import com.codemind.user.context.UserContext;
import com.codemind.user.dto.*;
import com.codemind.user.entity.User;
import com.codemind.user.mapper.UserMapper;
import com.codemind.user.service.UserService;
import com.codemind.user.util.JwtUtil;
import com.codemind.user.vo.AdminUserVO;
import com.codemind.user.vo.LoginVO;
import com.codemind.user.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Resource
    private JwtUtil jwtUtil;

    public UserServiceImpl(
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ){
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserVO register(RegisterDTO registerDTO) {

        User existingUser =
                userMapper.selectByUsername(registerDTO.getUsername());

        if (existingUser != null) {
            throw new BusinessException("用户名已存在");
        }

        User user = new User();

        user.setUsername(registerDTO.getUsername());
        user.setPassword(
                passwordEncoder.encode(registerDTO.getPassword())
        );
        user.setRole(UserConstants.USER);

        int rows = userMapper.insert(user);

        if (rows == 0) {
            throw new BusinessException("用户注册失败");
        }

        return convertToVO(user);
    }

    @Override
    public LoginVO login(LoginDTO loginDTO) {

        User user = userMapper.selectByUsername(loginDTO.getUsername());

        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }

        if(user.getStatus() != null
                && user.getStatus() == 0){

            throw new BusinessException(
                    "账号已注销"
            );
        }

        if (!passwordEncoder.matches(
                loginDTO.getPassword(),
                user.getPassword()
        )) {
            throw new BusinessException("用户名或密码错误");
        }

        String token = jwtUtil.createToken(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );


        LoginVO loginVO = new LoginVO();

        loginVO.setToken(token);

        loginVO.setUser(convertToVO(user));

        return loginVO;
    }

    @Override
    public UserVO getUserByUsername(String username) {

        User user = userMapper.selectByUsername(username);

        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        return convertToVO(user);
    }

    private UserVO convertToVO(User user) {

        UserVO userVO = new UserVO();

        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setRole(user.getRole());

        userVO.setNickname(user.getNickname());
        userVO.setEmail(user.getEmail());
        userVO.setPhone(user.getPhone());

        return userVO;
    }

    @Override
    public UserVO getCurrentUser() {


        Long userId = UserContext.getUserId();


        User user =
                userMapper.selectById(userId);


        if(user == null){

            throw new BusinessException(
                    "用户不存在"
            );
        }


        return convertToVO(user);

    }

    @Override
    public void updatePassword(
            UpdatePasswordDTO updatePasswordDTO
    ) {


        Long userId =
                UserContext.getUserId();


        User user =
                userMapper.selectById(userId);


        if(user == null){

            throw new BusinessException(
                    "用户不存在"
            );
        }


        boolean matches =
                passwordEncoder.matches(
                        updatePasswordDTO.getOldPassword(),
                        user.getPassword()
                );


        if(!matches){

            throw new BusinessException(
                    "旧密码错误"
            );
        }


        String newPassword =
                passwordEncoder.encode(
                        updatePasswordDTO.getNewPassword()
                );


        int rows =
                userMapper.updatePassword(
                        userId,
                        newPassword
                );


        if(rows == 0){

            throw new BusinessException(
                    "密码修改失败"
            );
        }

    }

    @Override
    public void updateProfile(
            UpdateProfileDTO updateProfileDTO
    ) {


        Long userId =
                UserContext.getUserId();


        int rows =
                userMapper.updateProfile(
                        userId,
                        updateProfileDTO.getNickname(),
                        updateProfileDTO.getEmail(),
                        updateProfileDTO.getPhone()
                );


        if(rows == 0){

            throw new BusinessException(
                    "个人资料修改失败"
            );
        }

    }

    @Override
    public void disableAccount() {


        Long userId =
                UserContext.getUserId();


        int rows =
                userMapper.disableUser(userId);


        if(rows == 0){

            throw new BusinessException(
                    "账号注销失败"
            );
        }
    }

    @Override
    public List<AdminUserVO> getUserList() {


        List<User> users =
                userMapper.selectAll();


        return users.stream()
                .map(this::convertToAdminVO)
                .toList();

    }

    private AdminUserVO convertToAdminVO(User user){

        AdminUserVO vo =
                new AdminUserVO();


        vo.setId(user.getId());

        vo.setUsername(
                user.getUsername()
        );

        vo.setRole(
                user.getRole()
        );

        vo.setStatus(
                user.getStatus()
        );

        vo.setNickname(
                user.getNickname()
        );

        vo.setEmail(
                user.getEmail()
        );

        vo.setPhone(
                user.getPhone()
        );


        return vo;
    }

    @Override
    public AdminUserVO getUserDetail(Long id) {


        User user =
                userMapper.selectById(id);


        if(user == null){

            throw new BusinessException(
                    "用户不存在"
            );

        }


        return convertToAdminVO(user);

    }

    @Override
    public void updateRole(
            UpdateRoleDTO updateRoleDTO
    ) {


        String role =
                updateRoleDTO.getRole();


        if(!UserConstants.USER.equals(role)
                &&
                !UserConstants.ADMIN.equals(role)){

            throw new BusinessException(
                    "角色不存在"
            );
        }


        int rows =
                userMapper.updateRole(
                        updateRoleDTO.getUserId(),
                        role
                );


        if(rows == 0){

            throw new BusinessException(
                    "用户不存在"
            );
        }

    }

    @Override
    public void updateStatus(
            UpdateStatusDTO updateStatusDTO
    ){

        Integer status =
                updateStatusDTO.getStatus();


        if(status != 0 && status != 1){

            throw new BusinessException(
                    "状态参数错误"
            );
        }


        int rows =
                userMapper.updateStatus(
                        updateStatusDTO.getUserId(),
                        status
                );


        if(rows == 0){

            throw new BusinessException(
                    "用户不存在"
            );
        }

    }
}
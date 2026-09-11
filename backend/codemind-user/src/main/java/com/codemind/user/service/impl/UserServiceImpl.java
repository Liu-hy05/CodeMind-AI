package com.codemind.user.service.impl;

import com.codemind.common.constants.UserConstants;
import com.codemind.common.exception.BusinessException;
import com.codemind.user.dto.LoginDTO;
import com.codemind.user.dto.RegisterDTO;
import com.codemind.user.entity.User;
import com.codemind.user.mapper.UserMapper;
import com.codemind.user.service.UserService;
import com.codemind.user.util.JwtUtil;
import com.codemind.user.vo.LoginVO;
import com.codemind.user.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        return userVO;
    }

}
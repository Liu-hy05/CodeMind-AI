package com.codemind.user.service.impl;

import com.codemind.common.constants.UserConstants;
import com.codemind.common.exception.BusinessException;
import com.codemind.user.dto.LoginDTO;
import com.codemind.user.dto.RegisterDTO;
import com.codemind.user.entity.User;
import com.codemind.user.mapper.UserMapper;
import com.codemind.user.service.UserService;
import com.codemind.user.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
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
        user.setPassword(registerDTO.getPassword());
        user.setRole(UserConstants.USER);

        int rows = userMapper.insert(user);

        if (rows == 0) {
            throw new BusinessException("用户注册失败");
        }

        return convertToVO(user);
    }

    @Override
    public UserVO login(LoginDTO loginDTO) {

        User user = userMapper.selectByUsername(loginDTO.getUsername());

        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }

        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }

        return convertToVO(user);
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
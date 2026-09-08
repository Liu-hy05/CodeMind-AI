package com.codemind.user.service;

import com.codemind.user.dto.LoginDTO;
import com.codemind.user.dto.RegisterDTO;
import com.codemind.user.vo.UserVO;

public interface UserService {

    UserVO register(RegisterDTO registerDTO);

    UserVO login(LoginDTO loginDTO);

    UserVO getUserByUsername(String username);

}
package com.codemind.user.mapper;

import com.codemind.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectByUsername(String username);

    int insert(User user);

}
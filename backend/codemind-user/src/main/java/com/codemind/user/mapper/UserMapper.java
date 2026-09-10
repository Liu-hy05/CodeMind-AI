package com.codemind.user.mapper;

import com.codemind.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("""
            SELECT *
            FROM user
            WHERE username = #{username}
            """)
    User selectByUsername(String username);


    @Options(
            useGeneratedKeys = true,
            keyProperty = "id"
    )
    @Insert("""
        INSERT INTO user
        (
            username,
            password,
            role
        )
        VALUES
        (
            #{username},
            #{password},
            #{role}
        )
        """)
    int insert(User user);

}
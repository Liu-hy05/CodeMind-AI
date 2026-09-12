package com.codemind.user.mapper;

import com.codemind.user.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {


    @Select("""
            SELECT *
            FROM user
            WHERE username = #{username}
            """)
    User selectByUsername(String username);

    @Select("""
            select 
            id,
            username,
            password,
            role,
            nickname,
            email,
            phone,
            status
            from user
            where id=#{id}
            """)
    User selectById(Long id);

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

    @Update("""
    update user
    set password = #{password}
    where id = #{userId}
""")
    int updatePassword(
            @Param("userId") Long userId,
            @Param("password") String password
    );

    @Update("""
        update user
        set nickname = #{nickname},
            email = #{email},
            phone = #{phone}
        where id = #{userId}
    """)
    int updateProfile(
            @Param("userId") Long userId,
            @Param("nickname") String nickname,
            @Param("email") String email,
            @Param("phone") String phone
    );

    @Update("""
    update user
    set status = 0
    where id = #{userId}
""")
    int disableUser(
            @Param("userId") Long userId
    );

    @Select("""
    select
        id,
        username,
        role,
        nickname,
        email,
        phone,
        status
    from user
""")
    List<User> selectAll();

    @Update("""
    update user
    set role = #{role}
    where id = #{userId}
""")
    int updateRole(
            @Param("userId") Long userId,
            @Param("role") String role
    );

    @Update("""
    update user
    set status = #{status}
    where id = #{userId}
""")
    int updateStatus(
            @Param("userId") Long userId,
            @Param("status") Integer status
    );
}
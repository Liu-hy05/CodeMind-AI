package com.codemind.user.context;

public class UserContext {

    private static final ThreadLocal<LoginUser> LOGIN_USER =
            new ThreadLocal<>();

    public static void setLoginUser(LoginUser loginUser) {
        LOGIN_USER.set(loginUser);
    }

    public static LoginUser getLoginUser() {
        return LOGIN_USER.get();
    }

    public static Long getUserId() {

        LoginUser loginUser = LOGIN_USER.get();

        return loginUser == null
                ? null
                : loginUser.getUserId();
    }

    public static String getUsername() {

        LoginUser loginUser = LOGIN_USER.get();

        return loginUser == null
                ? null
                : loginUser.getUsername();
    }

    public static String getRole() {

        LoginUser loginUser = LOGIN_USER.get();

        return loginUser == null
                ? null
                : loginUser.getRole();
    }

    public static void clear() {
        LOGIN_USER.remove();
    }
}
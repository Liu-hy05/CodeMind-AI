package com.codemind.user.interceptor;


import com.codemind.common.result.Result;
import com.codemind.user.annotation.RequireRole;
import com.codemind.user.context.UserContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RoleInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {


        // 不是Controller方法，直接放行
        if (!(handler instanceof HandlerMethod)) {

            return true;
        }


        HandlerMethod method =
                (HandlerMethod) handler;


        RequireRole requireRole =
                method.getMethodAnnotation(
                        RequireRole.class
                );


        // 没有权限注解
        if (requireRole == null) {

            return true;
        }


        String currentRole =
                UserContext.getRole();


        if (!requireRole.value()
                .equals(currentRole)) {


            response.setStatus(403);

            response.setContentType(
                    "application/json;charset=UTF-8"
            );


            response.getWriter()
                    .write(
                            Result.error(
                                    403,
                                    "无权限访问"
                            ).toString()
                    );


            return false;
        }


        return true;
    }
}
package com.codemind.user.filter;


import com.codemind.common.result.Result;
import com.codemind.user.context.LoginUser;
import com.codemind.user.context.UserContext;
import com.codemind.user.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;


import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private ObjectMapper objectMapper;


    @Override
    protected boolean shouldNotFilter(
            HttpServletRequest request
    ) {

        String path = request.getRequestURI();

        return "/user/login".equals(path)
                || "/user/register".equals(path);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authorization =
                request.getHeader("Authorization");

        try {

            if (authorization == null
                    || !authorization.startsWith("Bearer ")) {

                writeUnauthorized(
                        response,
                        "未登录，请先登录"
                );

                return;
            }

            String token = authorization.substring(7);

            Claims claims = jwtUtil.parseToken(token);

            Long userId = claims.get("userId", Long.class);
            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            LoginUser loginUser =
                    new LoginUser(userId, username, role);

            UserContext.setLoginUser(loginUser);

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {

            writeUnauthorized(
                    response,
                    "Token已过期"
            );

        } catch (JwtException | IllegalArgumentException e) {

            writeUnauthorized(
                    response,
                    "Token无效"
            );

        } finally {

            UserContext.clear();
        }
    }

    private void writeUnauthorized(
            HttpServletResponse response,
            String message
    ) throws IOException {

        response.setStatus(
                HttpServletResponse.SC_UNAUTHORIZED
        );

        response.setContentType(
                "application/json;charset=UTF-8"
        );

        Result<Void> result =
                Result.error(401, message);

        response.getWriter().write(
                objectMapper.writeValueAsString(result)
        );
    }
}
package com.cpf.client.filter;

import com.cpf.client.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName      JwtLoginFilter
 * Description    Jwt登录过滤器，继承UsernamePasswordAuthenticationFilter，
 * 认真成功后会在response首部返回生成的token，并直接返回
 * response，不再交给下级过滤器执行。
 *
 * @author CPF
 * @version 1.0
 * @date 2018/11/29 11:51
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {


    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.setAuthenticationManager(authenticationManager);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        response.addHeader("Authorization", JwtUtil.createToken(request.getParameter("username")));

        response.getWriter().print("Login in success! Keep the token secret!");

    }

}

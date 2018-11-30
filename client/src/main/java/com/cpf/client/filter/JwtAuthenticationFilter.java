package com.cpf.client.filter;

import com.cpf.client.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ClassName      JwtAuthenticationFilter
 * Description    JWT认证过滤器，访问受保护的url需要携带值为经过授权
 * 的token的Authorization首部。该Filter放在JwtLoginFilter
 * 后面
 *
 * @author CPF
 * @version 1.0
 * @date 2018/11/29 14:14
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String authorizationHeader = request.getHeader("Authorization");
        String username = JwtUtil.verifyToken(authorizationHeader);

        // 验证得到合法token
        if (username != null) {

            UsernamePasswordAuthenticationToken authentication;

            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("LIST"));
            // 返回用户凭证，将来可以根据UserDetails获取，
            authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        chain.doFilter(request, response);

    }

}

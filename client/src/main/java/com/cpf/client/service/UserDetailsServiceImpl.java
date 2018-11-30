package com.cpf.client.service;

import com.cpf.client.dao.UserRepository;
import com.cpf.client.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * ClassName      UserDetailsServiceImpl
 * Description    获取用户信息的接口实现
 *
 * @author CPF
 * @version 1.0
 * @date 2018/11/29 11:20
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        // 这里暂时用email代替密码
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getEmail(), emptyList());
    }
}

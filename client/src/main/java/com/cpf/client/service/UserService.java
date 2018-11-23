package com.cpf.client.service;

import com.cpf.client.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String getUserInfo(Integer id);

    String listAllUserInfo();

    String getUserInfoByName(String name);

    void addUser(User user);

    User getUserByName(String name);
}

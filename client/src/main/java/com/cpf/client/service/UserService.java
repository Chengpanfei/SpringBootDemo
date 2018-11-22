package com.cpf.client.service;

import com.cpf.client.pojo.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String getUserInfo(Integer id);

    String listAllUserInfo();

    String getUserInfoByName(String name);

    void addUser(UserEntity user);

    UserEntity getUserByName(String name);
}

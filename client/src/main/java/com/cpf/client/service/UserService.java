package com.cpf.client.service;

import com.cpf.client.pojo.User;
import org.springframework.stereotype.Service;

/**
 * 用户服务相关接口
 *
 * @author CPF
 */
@Service
public interface UserService {

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 用户相关信息
     */
    String getUserInfo(Integer id);

    /**
     * 获取所有用户信息
     *
     * @return 所有用户信息
     */
    String listAllUserInfo();

    /**
     * 根据指定用户名返回用户信息
     *
     * @param name 用户名
     * @return 指定用户名的用户信息
     */
    String getUserInfoByName(String name);

    /**
     * 新增用户
     *
     * @param user 将要添加的用户
     */
    void addUser(User user);

    /**
     * 根据用户名返回实体对象
     *
     * @param name 用户名
     * @return 指定用户名的实体对象
     */
    User getUserByName(String name);
}

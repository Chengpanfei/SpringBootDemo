package com.cpf.client.service;


import com.cpf.client.dao.UserRepository;
import com.cpf.client.pojo.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    /**
     * The class implements the {@link UserService} Interface!
     *
     * <p>用户相关服务的实现类！</p>
     */

    //    IDEA不推荐使用变量注入
    //    @Autowired
    //    UserRepository userRepository;
    // 使用构造器进行依赖注入
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    public UserServiceImp(UserRepository repo) {
        this.userRepository = repo;

    }

    /**
     * @param id，the id of the specified user.
     * @return String，the user info.
     * @author cpf
     * @see UserService
     * @since 1.8
     */
    @Override
    public String getUserInfo(Integer id) {
        String result = "null";
        Optional<UserEntity> option = userRepository.findById(id);
        if (option.isPresent()) {
            result = option.get().toString();
        }

        logger.warn("User for id:" + id + "  not found!");
        return result;
    }

    @Override
    public String listAllUserInfo() {
        List<UserEntity> list = userRepository.listUserEntity();
        StringBuilder str = new StringBuilder();

        for (UserEntity user : list) {
            str.append(user.toString());
        }
        return str.toString();
    }

    @Override
    public String getUserInfoByName(String name) {

        UserEntity user = userRepository.findUserByName(name);
        if (user != null) {
            return user.toString();
        }
        return "nothing found!";
    }

    @Override
    public UserEntity getUserByName(String name) {

        UserEntity user = userRepository.findUserByName(name);
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public void addUser(UserEntity user) {
        userRepository.save(user);
    }


}

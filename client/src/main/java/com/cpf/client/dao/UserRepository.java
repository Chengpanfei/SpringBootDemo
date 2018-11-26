package com.cpf.client.dao;

import com.cpf.client.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CPF
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据用户名查找用户
     *
     * @param userName 用户名
     * @return 根据指定用户名查找到的用户
     */
    User findByUsername(String userName);

    /**
     * 返回数据库中的所有用户，Query注解使用HQL
     * 可移植性高
     * TODO 添加分页功能
     *
     * @return 数据库中的所有用户
     */

    @Query("from User ")
    List<User> listUserEntity();

    /**
     * 通过具名查询参数查询
     *
     * @param name 用户名字
     * @return 根据用户名字得到的用户
     */

    @Query("from User where  name = :name")
    User findUserByName(@Param("name") String name);
}

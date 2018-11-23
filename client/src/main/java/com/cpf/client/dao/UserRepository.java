package com.cpf.client.dao;

import com.cpf.client.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String userName);

    @Query("from User ")
        //HQL可以避免各种不同的数据库
    List<User> listUserEntity();

    //通过具名查询参数查询
    @Query("from User where  name = :name")
    User findUserByName(@Param("name") String name);
}

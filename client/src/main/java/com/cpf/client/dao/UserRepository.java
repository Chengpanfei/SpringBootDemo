package com.cpf.client.dao;

import com.cpf.client.pojo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUsername(String userName);

    @Query("from UserEntity ")
        //HQL可以避免各种不同的数据库
    List<UserEntity> listUserEntity();

    //通过具名查询参数查询
    @Query("from UserEntity where  name = :name")
    UserEntity findUserByName(@Param("name") String name);
}

package com.cpf.client.dao;

import com.cpf.client.pojo.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
    Posts findPostsById(Long id);
}

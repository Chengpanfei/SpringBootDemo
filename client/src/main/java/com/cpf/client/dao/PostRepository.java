package com.cpf.client.dao;

import com.cpf.client.pojo.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CPF
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * 通过Id查找到文章
     *
     * @param id 博客文章的id
     * @return Posts对象
     */
    Post findPostsById(Long id);
}

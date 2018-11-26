package com.cpf.client.pojo;

import javax.persistence.*;

/**
 * 博客文章实体类（POJO）
 *
 * @author CPF
 */
@Entity
@Table(name = "posts")
public class Posts {

    @Column(name = "id")
    @Id
    private long id;

    @Column(name = "title", nullable = false)
    private String title;


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author")
    private User author;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}

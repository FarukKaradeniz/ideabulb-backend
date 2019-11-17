package com.farukkaradeniz.ideabulb.models.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Table(name = "COMMENTS")
@Entity(name = "COMMENTS")
public class Comment {
    @Id
    @Column(name = "id",
            unique = true,
            nullable = false
    )
    private String id;
    @Column(name = "content",
            length = 1_000
    )
    private String content;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date",
            nullable = false
    )
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment() {}

    public Comment(String content, Date createDate, User user, Post post) {
        this.content = content;
        this.createDate = createDate;
        this.user = user;
        this.post = post;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

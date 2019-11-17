package com.farukkaradeniz.ideabulb.models.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "POSTS")
@Table(name = "POSTS")
public class Post {
    @Id
    @Column(name = "id",
            unique = true,
            nullable = false
    )
    private String id;
    @Column(name = "title",
            nullable = false
    )
    private String title;
    @Column(name = "content",
            nullable = false,
            length = 8_000
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
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL
    )
    private List<Comment> comments;

    public Post() {}

    public Post(String title, String content, Date createDate, User user, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.user = user;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

package com.farukkaradeniz.ideabulb.models.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "USERS")
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "id",
            unique = true,
            nullable = false
    )
    private String id;
    @Column(name = "username",
            unique = true,
            nullable = false,
            length = 60,
            updatable = false
    )
    private String username;
    @Column(name = "password",
            nullable = false
    )
    private String password;
    @Column(name = "email",
            nullable = false,
            unique = true
    )
    private String email;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date",
            nullable = false
    )
    private Date createDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserImage userImage;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<Post> posts = new ArrayList<>();
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<Comment> comments = new ArrayList<>();

    @Column(name = "ROLES",
            nullable = false
    )
    private String roles; // Will contain a value like: USER,ADMIN or ADMIN or USER,ADMIN,ROBOT

    @Column(name = "ACTIVE",
            nullable = false)
    private boolean isActive = true;

    public User() {}

    public User(String username, String password, String email,
                Date createDate, UserImage userImage, List<Post> posts,
                List<Comment> comments, String roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.userImage = userImage;
        this.posts = posts;
        this.comments = comments;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UserImage getUserImage() {
        return userImage;
    }

    public void setUserImage(UserImage userImage) {
        this.userImage = userImage;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

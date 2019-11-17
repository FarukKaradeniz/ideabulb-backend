package com.farukkaradeniz.ideabulb.models.dtos;

import java.util.List;

public class UserDTO {
    private String id;
    private String username;
    private String email;
    private UserImageDTO userImage;
    private List<PostDTO> posts;

    public UserDTO() {}

    public UserDTO(String id, String username, String email,
                   UserImageDTO userImage, List<PostDTO> posts) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.userImage = userImage;
        this.posts = posts;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserImageDTO getUserImage() {
        return userImage;
    }

    public void setUserImage(UserImageDTO userImage) {
        this.userImage = userImage;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}

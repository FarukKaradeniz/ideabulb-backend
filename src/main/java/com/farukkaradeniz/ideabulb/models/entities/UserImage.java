package com.farukkaradeniz.ideabulb.models.entities;

import javax.persistence.*;

@Table(name = "USERIMAGES")
@Entity(name = "USERIMAGES")
public class UserImage {
    @Id
    @Column(name = "id",
            unique = true,
            nullable = false
    )
    private String id;
    @Column(name = "fullImage")
    private String fullImage = "https://pixabay.com/get/5fe7d6474c52b10ff3d89938b977692b083edbe25056744d75267b/blank-profile-picture-973460_640.png";

    @Column(name = "thumbnail")
    private String thumbnail = "https://pixabay.com/get/5fe7d6474c52b10ff3d89938b977692b083edbe25056744d75267b/blank-profile-picture-973460_640.png";

    @OneToOne
    @MapsId
    private User user;

    public UserImage() {}

    public UserImage(String id, User user) {
        this.id = id;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullImage() {
        return fullImage;
    }

    public void setFullImage(String fullImage) {
        this.fullImage = fullImage;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

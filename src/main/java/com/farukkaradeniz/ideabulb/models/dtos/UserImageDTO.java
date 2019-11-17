package com.farukkaradeniz.ideabulb.models.dtos;

public class UserImageDTO {
    private String id;
    private String fullImage;
    private String thumbnail;

    public UserImageDTO() {}

    public UserImageDTO(String id, String fullImage, String thumbnail) {
        this.id = id;
        this.fullImage = fullImage;
        this.thumbnail = thumbnail;
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
}

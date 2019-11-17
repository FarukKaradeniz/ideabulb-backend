package com.farukkaradeniz.ideabulb.models.dtos;

import java.util.Date;
import java.util.List;

public class PostDTO {
    private String id;
    private String title;
    private String content;
    private Date createDate;
    private String userId;
    private List<CommentDTO> comments;

    public PostDTO() {}

    public PostDTO(String id, String title, String content,
                   Date createDate, String userId, List<CommentDTO> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}

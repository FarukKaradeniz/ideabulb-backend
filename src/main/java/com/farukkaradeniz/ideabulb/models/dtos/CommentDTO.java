package com.farukkaradeniz.ideabulb.models.dtos;

import java.util.Date;

public class CommentDTO {
    private String id;
    private String content;
    private String userId;
    private String postId;
    private Date createDate;

    public CommentDTO() {}

    public CommentDTO(String id, String content, String userId,
                      String postId, Date createDate) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.postId = postId;
        this.createDate = createDate;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

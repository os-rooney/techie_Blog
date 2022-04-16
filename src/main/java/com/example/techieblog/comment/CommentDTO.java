package com.example.techieblog.comment;

import javax.validation.constraints.Size;

public class CommentDTO {

    @Size(min =1, max = 99)
    private String commentText;

    public CommentDTO(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }
}

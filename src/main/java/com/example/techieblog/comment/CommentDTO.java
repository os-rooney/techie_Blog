package com.example.techieblog.comment;

public class CommentDTO {

    private String commentText;

    public CommentDTO(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }
}

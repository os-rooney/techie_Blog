package com.example.techieblog.comment;

import javax.validation.constraints.Size;

public class CommentDTO {

    @Size(min =1, max = 99)
    private String comment;

    public CommentDTO(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}

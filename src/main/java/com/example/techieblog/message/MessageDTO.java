package com.example.techieblog.message;

import javax.validation.constraints.Size;

public class MessageDTO {

    @Size(min = 1)
    private String title;

    @Size(min = 1)
    private String description;

    @Size(min = 1)
    private String content;


    public MessageDTO(String title, String description, String content) {

        this.description = description;
        this.content = content;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
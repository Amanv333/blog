package com.blog.DTO;

import lombok.Data;

@Data
public class CommentDto {
    private long id;
    private String text;
    private String email;

}

package com.blog.DTO;

import com.blog.entity.Post;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    private String text;
    private String email;


}

package com.blog.service;

import com.blog.DTO.Postdto;
import com.blog.entity.Post;

public interface PostService {
    public Postdto CreatePost(Postdto post);

    void deletePost(long id);
}

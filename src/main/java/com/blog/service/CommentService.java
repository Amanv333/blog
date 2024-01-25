package com.blog.service;

import com.blog.DTO.CommentDto;

public interface CommentService {
   CommentDto createComment(CommentDto dto,long postId);
}

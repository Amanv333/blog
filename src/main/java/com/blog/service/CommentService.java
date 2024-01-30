package com.blog.service;

import com.blog.DTO.CommentDto;

public interface CommentService {
   CommentDto createComment(CommentDto dto,long postId);

   void deleteById(long id);

   void updateComment(long id, CommentDto commentDto);
}

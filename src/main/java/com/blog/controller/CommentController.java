package com.blog.controller;

import com.blog.DTO.CommentDto;
import com.blog.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto dto, @RequestParam long postId){
        CommentDto dto1 = commentService.createComment(dto,postId);
        return new ResponseEntity<>(dto1, HttpStatus.CREATED);
    }
}

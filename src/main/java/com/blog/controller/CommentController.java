package com.blog.controller;

import com.blog.DTO.CommentDto;
import com.blog.service.CommentService;
import com.blog.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    //http://localhost:8080/api/comments?postId=1
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto dto, @RequestParam long postId){
        CommentDto dto1 = commentService.createComment(dto,postId);
        return new ResponseEntity<>(dto1, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/comments/2
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable long id){
        commentService.deleteById(id);
        return  new ResponseEntity<>("Comment is deleted",HttpStatus.OK);
    }
    //http://localhost:8080/api/comments/2
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable long id,@RequestBody CommentDto commentDto){
        commentService.updateComment(id,commentDto);
        return new ResponseEntity<>("comment is updated",HttpStatus.OK);
    }
}

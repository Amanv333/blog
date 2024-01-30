package com.blog.service.impl;

import com.blog.DTO.CommentDto;
import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourseNotFoundException;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto dto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourseNotFoundException("Post is not available with id : " + postId)
        );
        Comment comment = modelMapper.map(dto, Comment.class);
        comment.setPost(post);
        Comment saved = commentRepository.save(comment);
        return modelMapper.map(saved, CommentDto.class);
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);

    }

    @Override
    public void updateComment(long id, CommentDto commentDto) {
        Comment comment1 = commentRepository.findById(id).orElseThrow(
                () -> new ResourseNotFoundException("comment not found by id : " + id));
        comment1.setText(commentDto.getText());
        comment1.setEmail(commentDto.getEmail());
        commentRepository.save(comment1);

    }
}

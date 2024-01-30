package com.blog.service.impl;

import com.blog.DTO.CommentDto;
import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourseNotFoundException;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(CommentDto dto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourseNotFoundException("Post is not available with id : " + postId)
        );
        Comment comment = new Comment();
        comment.setEmail(dto.getEmail());
        comment.setText(dto.getText());
        comment.setPost(post);

        Comment saved = commentRepository.save(comment);

        CommentDto cmtdto= new CommentDto();
        cmtdto.setId(saved.getId());
        cmtdto.setEmail(saved.getEmail());
        cmtdto.setText(saved.getText());

        return cmtdto;


    }
}

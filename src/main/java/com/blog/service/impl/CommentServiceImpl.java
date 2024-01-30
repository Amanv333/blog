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

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper=modelMapper;
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

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourseNotFoundException("post is not found by id : " + id));
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()->new ResourseNotFoundException("Comment is not found by id : "+id));
        Comment c = mapToEntity(commentDto);
        c.setId(comment.getId());
        c.setPost(post);
        Comment saved = commentRepository.save(c);
        return mapToDto(saved);




    }
    public CommentDto mapToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }

    public Comment mapToEntity(CommentDto dto){
        return modelMapper.map(dto, Comment.class);
    }
}

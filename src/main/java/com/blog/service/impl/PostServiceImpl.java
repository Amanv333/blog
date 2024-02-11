package com.blog.service.impl;

import com.blog.DTO.Postdto;
import com.blog.entity.Post;
import com.blog.exception.ResourseNotFoundException;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository repository;


    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Postdto CreatePost(Postdto dto) {
       Post post = mapToPost(dto);
        Post saved = repository.save(post);
        return mapToDto(saved);
    }

    @Override
    public void deletePost(long id) {
        Post post = repository.findById(id).orElseThrow(
                ()->new ResourseNotFoundException("post not found by : "+id)
        );
        repository.deleteById(id);
    }

    @Override
    public void updatePost(long id, Postdto dto) {
        Post post = repository.findById(id).orElseThrow(
                ()->new ResourseNotFoundException("post not found by : "+id)
        );
        post.setDescription(dto.getDescription());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        repository.save(post);
    }

    public Postdto getPostById(long id) {
        Post post = repository.findById(id).orElseThrow(
                ()->new ResourseNotFoundException("post not found by : "+id)
        );
        return mapToDto(post);
    }

    public List<Postdto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())) ? Sort.by(sortBy) : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> pagePosts = repository.findAll(pageable);
        List<Post> posts= pagePosts.getContent();

        List<Postdto> dtos= posts.stream().map(this::mapToDto).collect(Collectors.toList());
        return dtos;
    }

    public Postdto mapToDto(Post post){
        Postdto newDto = new Postdto();
        newDto.setId(post.getId());
        newDto.setTitle(post.getTitle());
        newDto.setDescription(post.getDescription());
        newDto.setContent(post.getContent());
        return newDto;

    }

    public Post mapToPost(Postdto dto){
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());
        return post;
    }
}

package com.blog.controller;

import com.blog.DTO.Postdto;
import com.blog.entity.Post;
import com.blog.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class RestPostController {

    @Autowired
    private PostServiceImpl postService;

    public RestPostController(PostServiceImpl postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<?> savePost(@RequestBody Postdto post){

        postService.CreatePost(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/particular")
    public ResponseEntity<Postdto> getPostById(@RequestParam long id){

        Postdto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/post?pageNo=0&pageSize=3&sortBy=title&sortDir
    @GetMapping
    public List<Postdto> getAllpost(
            @RequestParam(name="pageNo",required = false,defaultValue = "0") int pageNo,
            @RequestParam(name="pageSize",required = false,defaultValue = "3") int pageSize,
            @RequestParam(name="sortBy",required = false,defaultValue = "id") String sortBy,
            @RequestParam(name="sortDir",required = false,defaultValue = "asc") String sortDir

    ){
        List<Postdto> postDtos = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return postDtos;
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePostById(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted by id : "+id,HttpStatus.OK);
    }

}

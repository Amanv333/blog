package com.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data//get all the getters and setters
@Entity
@Table(name="posts")
@AllArgsConstructor//get all the constructor with arguments
@NoArgsConstructor//get all the constructor without arguments
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;


}

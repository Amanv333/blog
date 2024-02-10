package com.blog.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private String email;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    // Constructors, getters, and setters (generated by Lombok)...

    // You may add other fields and methods as needed.
}

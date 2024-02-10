package com.blog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SighnUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}

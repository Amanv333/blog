package com.blog.service;

import com.blog.DTO.SighnUpDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    public ResponseEntity<?> saveUser(SighnUpDto dto);
}

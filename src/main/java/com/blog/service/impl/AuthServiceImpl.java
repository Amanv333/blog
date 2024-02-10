package com.blog.service.impl;

import com.blog.DTO.SighnUpDto;
import com.blog.entity.User;
import com.blog.repository.UserRepository;
import com.blog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<?> saveUser(SighnUpDto sighnUpDto) {
        if(userRepository.existsByUsername((sighnUpDto.getUsername()))){
            return new ResponseEntity<>("UserName is already taken", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail((sighnUpDto.getEmail()))){
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(sighnUpDto.getName());
        user.setEmail(sighnUpDto.getEmail());
        user.setUsername(sighnUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(sighnUpDto.getPassword()));

        userRepository.save(user);

        return new ResponseEntity<>("user saved successfully",HttpStatus.OK);
    }
}

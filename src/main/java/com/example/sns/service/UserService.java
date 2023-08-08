package com.example.sns.service;

import com.example.sns.entity.User;
import com.example.sns.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void updateImage( MultipartFile Image, Authentication authentication){
        String username = authentication.getName();
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty())
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        User userEntity = user.get();


        if (!userEntity.getUsername().equals(authentication.getName())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String profileDir = String.format("media/profile/%s/",username);
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String originalFilename = Image.getOriginalFilename();
        String[] fileNameSplit = originalFilename.split("\\.");
        String extension = fileNameSplit[fileNameSplit.length - 1];
        String profileFilename = "profile." + extension;

        String profilePath = profileDir + profileFilename;

        try {
            Image.transferTo(Path.of(profilePath));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userEntity.setProfileImg(String.format("/static/%s/%s",username, profileFilename));
        userRepository.save(userEntity);
    }

    }


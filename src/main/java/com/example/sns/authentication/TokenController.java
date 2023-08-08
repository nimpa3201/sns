package com.example.sns.authentication;


import com.example.sns.service.ArticleService;
import com.example.sns.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
@Data
@Slf4j
@RestController
@RequestMapping("token")
public class TokenController {
    // UserDetailsManager: 사용자 정보 회수
    // PasswordEncoder: 비밀번호 대조용 인코더
    private final UserService userService;
    private final UserDetailsManager manager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;




    public TokenController(
            UserDetailsManager manager,
            PasswordEncoder passwordEncoder,
            JwtTokenUtils jwtTokenUtils,
            UserService userService)
    {
        this.manager = manager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtils = jwtTokenUtils;
        this.userService = userService;
    }

    @PostMapping("/issue")
    public JwtTokenDto issueJwt(@RequestBody JwtRegisterDto dto) {
        // 사용자 정보 회수
        // 로그인 loadUserByUsername
        UserDetails userDetails
                = manager.loadUserByUsername(dto.getUsername());

        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        JwtTokenDto response = new JwtTokenDto();
        response.setToken(jwtTokenUtils.generateToken(userDetails));
        return response;
    }

    @PostMapping("/register")
    public JwtRegisterDto registerUser(@RequestBody JwtRegisterDto dto){
        if (dto.getPassword().equals(dto.getPasswordCheck())) {
            manager.createUser(CustomUserDetails.builder()
                    .username(dto.getUsername())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .phone(dto.getPhone())
                    .address(dto.getAddress())
                    .email(dto.getEmail())
                    .build());
        }
        return dto;
    }
    @PutMapping("/profile")
    public Map<String, String> addImage(
            @RequestParam("image") MultipartFile Image,
            Authentication authentication){


        Map<String, String> responsebody = new HashMap<>();
        responsebody.put("messege","이미지가 등록되었습니다.");
        userService.updateImage(Image,authentication);
        return responsebody;

    }


}



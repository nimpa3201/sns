package com.example.sns.service;

import com.example.sns.authentication.JwtTokenUtils;
import com.example.sns.dto.ArticleDTO;
import com.example.sns.entity.ArticleImage;
import com.example.sns.entity.User;
import com.example.sns.repository.ArticleImageRepository;
import com.example.sns.repository.ArticleRepository;
import com.example.sns.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.sns.entity.Article;
import com.example.sns.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;



@Data
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleImageRepository articleImgRepository;
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;


    public void createdFeed(ArticleDTO dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptionalEntity
                = userRepository.findByUsername(username);
        if (userOptionalEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Article newArticle = new Article();
        newArticle.setUser(userOptionalEntity.get());
        newArticle.setTitle(dto.getTitle());
        newArticle.setContent(dto.getContent());
        newArticle.setUsername(userOptionalEntity.get().getUsername());

        articleRepository.save(newArticle);


    }

    public  void updateImages(Long id, MultipartFile Image) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Article> articleEntity = articleRepository.findById(id);

        if (!articleEntity.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }



        Article article = articleEntity.get();
        if (!article.getUser().getUsername().equals(username)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String profileDir = String.format("media/feed/%d/", id);
        try {
            Files.createDirectories(Path.of(profileDir));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String originalFilename = Image.getOriginalFilename();
        String feedFilename = originalFilename;

        String profilePath = profileDir + feedFilename;

        try {
            Image.transferTo(Path.of(profilePath));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ArticleImage articleImage = new ArticleImage();
        articleImage.setImageUrl((String.format("/static/feed/%d/%s", id, feedFilename)));
        articleImage.setArticle(articleEntity.get());

        articleImgRepository.save(articleImage);

    }

}

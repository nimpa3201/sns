package com.example.sns.controller.article;


import com.example.sns.dto.ArticleDTO;
import com.example.sns.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor

public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public Map<String, String> create(@RequestBody ArticleDTO dto) {
        articleService.createdFeed(dto);
        Map<String, String> response = new HashMap<>();
        response.put("message:", "게시물 업로드 완료");
        return response;
    }
    @PostMapping("/{feedId}")
    public Map<String,String> updateImages(@PathVariable("feedId") Long id,
                                          @RequestParam("image") MultipartFile image
                                          ) {
        articleService.updateImages(id,image);
        Map<String,String> response = new HashMap<>();
        response.put("message:","이미지 추가");
        return response;
    }

    }


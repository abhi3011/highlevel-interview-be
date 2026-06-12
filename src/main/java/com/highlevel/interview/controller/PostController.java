package com.highlevel.interview.controller;

import com.highlevel.interview.dto.PostCreateRequestDto;
import com.highlevel.interview.dto.PostCreateResponseDto;
import com.highlevel.interview.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

  @Autowired private PostService postService;

  @PostMapping
  public ResponseEntity<PostCreateResponseDto> createPost(
      @Valid @RequestBody PostCreateRequestDto request) {
    PostCreateResponseDto response = postService.createPost(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}

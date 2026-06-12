package com.highlevel.interview.service;

import com.highlevel.interview.dto.PostCreateRequestDto;
import com.highlevel.interview.dto.PostCreateResponseDto;
import com.highlevel.interview.model.PostEntity;
import com.highlevel.interview.model.UserEntity;
import com.highlevel.interview.repository.PostRepository;
import com.highlevel.interview.repository.UserRepository;
import com.highlevel.interview.util.FcStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostService {

  @Autowired private PostRepository postRepository;

  @Autowired private UserRepository userRepository;

  public PostCreateResponseDto createPost(PostCreateRequestDto request) {
    UserEntity author =
        userRepository
            .findByUserId(request.getUserId())
            .orElseThrow(
                () -> new RuntimeException("User not found for userId: " + request.getUserId()));

    PostEntity post =
        PostEntity.builder()
            .postId(FcStringUtils.getUuid())
            .title(request.getTitle())
            .body(request.getBody())
            .author(author)
            .authorHandle(author.getHandle())
            .build();

    PostEntity savedPost = postRepository.save(post);
    log.info("Created post with postId: {} for userId: {}", savedPost.getPostId(), author.getUserId());

    return PostCreateResponseDto.builder().postId(savedPost.getPostId()).build();
  }
}

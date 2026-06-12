package com.highlevel.interview.service;

import com.highlevel.interview.dto.UserCreateRequestDto;
import com.highlevel.interview.dto.UserCreateResponseDto;
import com.highlevel.interview.model.UserEntity;
import com.highlevel.interview.repository.UserRepository;
import com.highlevel.interview.util.FcStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

  @Autowired private UserRepository userRepository;

  public UserCreateResponseDto createUser(UserCreateRequestDto request) {
    UserEntity user =
        UserEntity.builder()
            .userId(FcStringUtils.getUuid())
            .name(request.getName())
            .email(request.getEmail())
            .handle(request.getHandle())
            .dob(request.getDob())
            .passwordHash(request.getPassword())
            .followerCount(0L)
            .build();

    UserEntity savedUser = userRepository.save(user);
    log.info("Created user with userId: {}", savedUser.getUserId());

    return UserCreateResponseDto.builder().userId(savedUser.getUserId()).build();
  }
}

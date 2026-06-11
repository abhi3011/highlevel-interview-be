package com.firstclub.membership.controller;

import com.firstclub.membership.dto.UserResponseDto;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  private static final List<String> RANDOM_NAMES =
      List.of(
          "Ada Lovelace",
          "Alan Turing",
          "Grace Hopper",
          "Linus Torvalds",
          "Margaret Hamilton",
          "Dennis Ritchie",
          "Barbara Liskov",
          "Ken Thompson");

  @GetMapping("/random")
  public UserResponseDto getRandomUser() {
    String name = RANDOM_NAMES.get(ThreadLocalRandom.current().nextInt(RANDOM_NAMES.size()));
    return UserResponseDto.builder().name(name).build();
  }
}

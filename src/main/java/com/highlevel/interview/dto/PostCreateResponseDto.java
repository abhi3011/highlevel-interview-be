package com.highlevel.interview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreateResponseDto {

  @JsonProperty("postId")
  private String postId;
}

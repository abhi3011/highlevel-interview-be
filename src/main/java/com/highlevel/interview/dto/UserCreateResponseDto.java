package com.highlevel.interview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateResponseDto {

  @JsonProperty("userId")
  private String userId;
}

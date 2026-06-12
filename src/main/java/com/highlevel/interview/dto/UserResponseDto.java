package com.highlevel.interview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

  @JsonProperty("name")
  private String name;
}

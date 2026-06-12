package com.highlevel.interview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.highlevel.interview.constant.DbConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequestDto {

  @NotBlank(message = "title must not be empty")
  @Size(
      max = DbConstant.POST_TITLE_MAX_LEN,
      message = "title must be at most " + DbConstant.POST_TITLE_MAX_LEN + " characters")
  @JsonProperty("title")
  private String title;

  @NotBlank(message = "body must not be empty")
  @Size(
      max = DbConstant.POST_BODY_MAX_LEN,
      message = "body must be at most " + DbConstant.POST_BODY_MAX_LEN + " characters")
  @JsonProperty("body")
  private String body;

  @NotBlank(message = "userId must not be empty")
  @JsonProperty("userId")
  private String userId;
}

package com.highlevel.interview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {

  @NotBlank(message = "name must not be empty")
  @JsonProperty("name")
  private String name;

  @NotBlank(message = "email must not be empty")
  @JsonProperty("email")
  private String email;

  @NotBlank(message = "handle must not be empty")
  @JsonProperty("handle")
  private String handle;

  @NotNull(message = "dob must not be null")
  @JsonProperty("dob")
  private Timestamp dob;

  @NotBlank(message = "password must not be empty")
  @JsonProperty("password")
  private String password;
}

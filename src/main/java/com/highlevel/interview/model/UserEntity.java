package com.highlevel.interview.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.highlevel.interview.constant.DbConstant;
import com.highlevel.interview.repository.UserRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = UserRepository.TABLE_NAME)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = DbConstant.COL_LEN_255, message = "name must be less than 255 characters")
  @Column(name = "name")
  private String name;

  @Column(name = "userId")
  private String userId;

  // todo : add valid email check
  @Size(max = DbConstant.COL_LEN_255, message = "name must be less than 255 characters")
  @Column(name = "email")
  private String email;

  // todo : can use uuid as handle
  @Size(max = DbConstant.COL_LEN_255, message = "handle must be less than 255 characters")
  @Column(name = "handle", unique = true)
  private String handle;

  @Column(name = "dob")
  private Timestamp dob;

  @Column(name = "follower_count")
  private Long followerCount;

  @Column(name = "password_hash", nullable = false)
  private String passwordHash;

  @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
  @Builder.Default
  private List<PostEntity> posts = new ArrayList<>();

  @PrePersist
  @PreUpdate
  private void validateDob() {
    if (dob == null) {
      throw new IllegalStateException("dob cannot be null");
    }
    LocalDate birthDate = dob.toLocalDateTime().toLocalDate();
    int age = Period.between(birthDate, LocalDate.now()).getYears();
    if (age < DbConstant.MIN_AGE_YEARS) {
      throw new IllegalStateException("User must be at least " + DbConstant.MIN_AGE_YEARS + " years old");
    }
  }
}

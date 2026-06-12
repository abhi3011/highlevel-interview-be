package com.highlevel.interview.repository;

import com.highlevel.interview.model.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  String TABLE_NAME = "users";

  Optional<UserEntity> findByUserId(String userId);
}

package com.highlevel.interview.repository;

import com.highlevel.interview.model.UserEntity;
import com.highlevel.interview.model.UserFollowRelationEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowRelationRepository
    extends JpaRepository<UserFollowRelationEntity, Long> {

  String TABLE_NAME = "user_follow_relations";

  Optional<UserFollowRelationEntity> findByFollowerAndFollowee(
      UserEntity follower, UserEntity followee);

  boolean existsByFollowerAndFollowee(UserEntity follower, UserEntity followee);

  List<UserFollowRelationEntity> findByFollower(UserEntity follower);

  List<UserFollowRelationEntity> findByFollowee(UserEntity followee);
}

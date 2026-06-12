package com.highlevel.interview.repository;

import com.highlevel.interview.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

  String TABLE_NAME = "posts";
}

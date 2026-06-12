package com.highlevel.interview.service;

import com.highlevel.interview.repository.UserFollowRelationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserFollowRelationService {

  @Autowired private UserFollowRelationRepository userFollowRelationRepository;
}

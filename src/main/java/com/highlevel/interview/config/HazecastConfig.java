package com.highlevel.interview.config;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazecastConfig {

  @Bean
  public Config hazelcastConfig() {
    Config config = new Config();
    config.setClusterName("local-development-cluster");

    var network = config.getNetworkConfig();
    var join = network.getJoin();

    join.getMulticastConfig().setEnabled(false);

    // TODO: pick from config in prod
    join.getTcpIpConfig().setEnabled(true).addMember("127.0.0.1");

    return config;
  }

  @Bean
  public HazelcastInstance hazelcastInstance(Config hazelcastConfig) {
    return Hazelcast.newHazelcastInstance(hazelcastConfig);
  }
}

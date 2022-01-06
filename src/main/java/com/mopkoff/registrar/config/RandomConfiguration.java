package com.mopkoff.registrar.config;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RandomConfiguration {

  @Bean
  @Scope("prototype")
  public Random random() {
    return new Random();
  }
}

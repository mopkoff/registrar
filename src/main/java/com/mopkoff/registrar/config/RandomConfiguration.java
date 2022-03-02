package com.mopkoff.registrar.config;

import java.util.Random;

import com.github.javafaker.Faker;
import com.mopkoff.registrar.service.utils.CustomRandom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RandomConfiguration {

  @Bean
  @Scope("prototype")
  public CustomRandom random() {
    return new CustomRandom();
  }

  @Bean
  @Scope("prototype")
  public Faker faker() {
    return new Faker(random());
  }
}

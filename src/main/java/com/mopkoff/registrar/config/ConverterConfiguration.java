package com.mopkoff.registrar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

@Configuration
public class ConverterConfiguration {

  @Bean
  public GenericConversionService registrarConversionService() {
    return new DefaultConversionService();
  }
}

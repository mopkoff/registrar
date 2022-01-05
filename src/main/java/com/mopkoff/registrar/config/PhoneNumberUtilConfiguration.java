package com.mopkoff.registrar.config;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhoneNumberUtilConfiguration {

  @Bean
  PhoneNumberUtil phoneNumberUtil () {
    return PhoneNumberUtil.getInstance();
  }
}

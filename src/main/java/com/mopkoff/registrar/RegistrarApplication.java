package com.mopkoff.registrar;

import com.mopkoff.registrar.config.OnlineSimProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableConfigurationProperties(OnlineSimProperties.class)
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RegistrarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrarApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

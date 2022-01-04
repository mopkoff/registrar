package com.mopkoff.registrar.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.onlinesim")
public class OnlineSimProperties {
    private String url;
    private String apiKey;
}

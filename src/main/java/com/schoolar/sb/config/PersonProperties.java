package com.schoolar.sb.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "persons")
public class PersonProperties {

    private Api api;

    @Data
    public static class Api {
        private String key;
        private String url;
    }

}

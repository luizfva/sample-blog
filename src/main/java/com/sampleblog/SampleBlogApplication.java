package com.sampleblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.sampleblog.repository")
@EnableElasticsearchRepositories(basePackages = "com.sampleblog.search")
@EnableAutoConfiguration
public class SampleBlogApplication {
    public static void main(final String[] args) {
        SpringApplication.run(SampleBlogApplication.class, args);
    }
}
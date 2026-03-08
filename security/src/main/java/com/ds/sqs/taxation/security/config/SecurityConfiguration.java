package com.ds.sqs.taxation.security.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.ds.sqs.taxation.security.repository"})
@EntityScan(basePackages = {"com.ds.sqs.taxation.security.entity"})
public class SecurityConfiguration {
}

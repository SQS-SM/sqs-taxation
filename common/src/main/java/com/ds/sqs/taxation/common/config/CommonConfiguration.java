package com.ds.sqs.taxation.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.ds.sqs.taxation.common.repository"})
@EntityScan(basePackages = {
        "com.ds.sqs.taxation.common.entity"})
@Configuration
public class CommonConfiguration {
}

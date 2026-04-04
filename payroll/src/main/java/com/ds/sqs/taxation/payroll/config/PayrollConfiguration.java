package com.ds.sqs.taxation.payroll.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.ds.sqs.taxation.payroll.repository"})
@EntityScan(basePackages = {"com.ds.sqs.taxation.payroll.entity"})
public class PayrollConfiguration {
}

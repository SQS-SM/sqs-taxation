package com.ds.sqs.taxation.api.config;

import com.ds.sqs.taxation.api.filter.SqlQueryCollector;
import com.zaxxer.hikari.HikariDataSource;
import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class DatasourceProxyConfig {
    @Bean(name = "realDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource realDataSource(DataSourceProperties properties) {
        return properties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Primary
    public DataSource proxiedDataSource(@Qualifier("realDataSource") DataSource dataSource) {
        return ProxyDataSourceBuilder.create(dataSource)
                .name("DB-Proxy")
                .listener(new QueryExecutionListener() {
                    @Override
                    public void beforeQuery(ExecutionInfo execInfo, List<net.ttddyy.dsproxy.QueryInfo> queryInfoList) {
                    }

                    @Override
                    public void afterQuery(ExecutionInfo execInfo, List<net.ttddyy.dsproxy.QueryInfo> queryInfoList) {
                        queryInfoList.forEach(q -> {
                            List<Object> allParams = q.getParametersList().stream()
                                    .flatMap(List::stream)
                                    .flatMap(p -> p.getArgs() != null ? Arrays.stream(p.getArgs()) : Stream.empty())
                                    .toList();
                            SqlQueryCollector.add(q.getQuery(), allParams, execInfo.getElapsedTime());
                        });
                    }
                })
                .build();
    }
}
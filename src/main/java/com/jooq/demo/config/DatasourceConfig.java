package com.jooq.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DatasourceConfig {

    private DatasourceConfig() {
    }

    public static DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/jooqdemo");
        config.setUsername("jooqdemo");
        config.setPassword("jooqdemo");
        config.setAutoCommit(true);
        config.setMaximumPoolSize(32);
        return new HikariDataSource(config);
    }
}

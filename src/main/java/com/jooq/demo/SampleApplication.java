package com.jooq.demo;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;


public class SampleApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleApplication.class);
    private static final String CHANGELOG_FILE = "liquibase/postgresql.xml";


    public static void main(String[] args) throws Exception {

        LOGGER.info("==> Connecting to DB");
        Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/sakilapg", "sakilapg", "sakilapg");
        connection.setAutoCommit(false);

        LOGGER.info("==> Migrating DB");
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

        Liquibase liquibase = new liquibase.Liquibase(CHANGELOG_FILE, new ClassLoaderResourceAccessor(), database);
        liquibase.update(new Contexts(), new LabelExpression());
    }

}

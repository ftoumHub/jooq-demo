package com.jooq.demo;

import com.github.javafaker.Faker;
import com.jooq.demo.config.DatasourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.jooq.impl.DSL;
import org.jooq.sources.tables.records.CustomersRecord;

import javax.sql.DataSource;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.jooq.SQLDialect.POSTGRES;
import static org.jooq.sources.tables.Customers.CUSTOMERS;

@Slf4j
public class App {

    private static final Faker faker = new Faker();

    public static void main(String[] args) {
        final DataSource dataSource = DatasourceConfig.createDataSource();

        DSL.using(dataSource, POSTGRES).truncateTable(CUSTOMERS).execute();

        initDatabase(dataSource);

        final CustomersRecord customersRecord = DSL.using(dataSource, POSTGRES)
                .selectFrom(CUSTOMERS)
                .where(CUSTOMERS.AGE.between(18, 40))
                .fetchAny();

        log.info("Customer record:\n{}", customersRecord);
    }

    private static void initDatabase(DataSource dataSource) {
        IntStream.range(0, 50).forEach(__ ->
            DSL.using(dataSource, POSTGRES)
                    .insertInto(CUSTOMERS)
                    .set(CUSTOMERS.CUSTOMER_ID, UUID.randomUUID())
                    .set(CUSTOMERS.AGE, (int)(Math.random() * 100))
                    .set(CUSTOMERS.FIRSTNAME, faker.name().firstName())
                    .set(CUSTOMERS.SURNAME, faker.name().lastName())
                    .execute()
        );
    }
}

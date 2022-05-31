package com.jooq.demo;

import org.jooq.sources.tables.Actor;
import org.jooq.sources.tables.FilmActor;
import org.junit.jupiter.api.Test;

import static org.jooq.impl.DSL.*;
import static org.jooq.sources.Tables.*;
import static org.jooq.sources.tables.Actor.ACTOR;

public class Demo_SELECT extends Demo {

    @Test
    void SELECT_FROM_ACTOR() {
        final Actor a = ACTOR.as("a");
        final FilmActor fa = FILM_ACTOR.as("fa");

        dsl().select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .fetch();
    }

    @Test
    void SELECT_FROM_ACTOR_GROUPING_BY() {
        dsl().select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME, count().as("nb"))
                .from(ACTOR)
                .join(FILM_ACTOR).on(ACTOR.ACTOR_ID.eq(FILM_ACTOR.ACTOR_ID))
                .groupBy(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .fetch();
    }

    @Test
    void SELECT_FROM_ACTOR_UNION_CUSTOMER() {
        dsl().select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                .from(ACTOR)
                .union(
                        select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
                                .from(CUSTOMER)
                )
                .fetch();
    }

    @Test
    void SELECT_CUSTOMERS_WHO_ARE_ACTORS() {
        dsl().select(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
                .from(CUSTOMER)
                .where(
                        row(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME)
                                .in(select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME)
                                .from(ACTOR))
                )
                .fetch();
    }

    @Test
    void SELECT_WITH_GROUP_CONCAT_DISTINCT() {
        final Actor a = ACTOR.as("a");
        final FilmActor fa = FILM_ACTOR.as("fa");

        dsl().select(a.FIRST_NAME, a.LAST_NAME, count(), groupConcatDistinct(CATEGORY.NAME).separator(", "))
                .from(a)
                .join(fa)
                .on(a.ACTOR_ID.eq(fa.ACTOR_ID))
                .join(FILM)
                .on(FILM.FILM_ID.eq(fa.FILM_ID))
                .join(FILM_CATEGORY)
                .on(FILM_CATEGORY.FILM_ID.eq(FILM.FILM_ID))
                .join(CATEGORY)
                .on(CATEGORY.CATEGORY_ID.eq(FILM_CATEGORY.CATEGORY_ID))
                .groupBy(a.ACTOR_ID, a.FIRST_NAME, a.LAST_NAME)
                .having(count().gt(40))
                .orderBy(count().desc())
                .fetch();

    }
}

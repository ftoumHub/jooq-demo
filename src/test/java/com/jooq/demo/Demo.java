package com.jooq.demo;

import com.jooq.demo.config.DatasourceConfig;
import org.jooq.DSLContext;
import org.jooq.conf.RenderKeywordStyle;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

import static org.jooq.SQLDialect.POSTGRES;

public class Demo {

    public static DSLContext dsl() {
        final DSLContext dsl = DSL.using(
                DatasourceConfig.createDataSource(),
                POSTGRES,
                new Settings()
                        .withRenderFormatted(true)
                        .withRenderNameStyle(RenderNameStyle.QUOTED)
                        .withRenderKeywordStyle(RenderKeywordStyle.UPPER)
                        .withRenderSchema(false)
        );
        return dsl;
    }
}

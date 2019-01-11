package com.github.charlemaznable.varys;

import com.github.charlemaznable.lang.pool.PoolProxy;
import com.github.charlemaznable.lang.pool.PooledObjectCreator;
import com.github.charlemaznable.varys.api.Query;
import lombok.Synchronized;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class Varys {

    private final Config config;
    private Query queryProxy;

    public Varys(Config config) {
        this.config = config;
    }

    @Synchronized
    public Query query() {
        if (null == queryProxy) {
            GenericObjectPoolConfig<Query> queryPoolConfig = new GenericObjectPoolConfig<>();
            queryPoolConfig.setMaxTotal(config.getQueryPoolMaxTotal());
            queryPoolConfig.setMaxIdle(config.getQueryPoolMaxIdle());
            queryPoolConfig.setMinIdle(config.getQueryPoolMinIdle());
            queryProxy = PoolProxy.create(new PooledObjectCreator<Query>() {
                @Override
                public Query create() {
                    return new Query().init(config);
                }
            }, queryPoolConfig);
        }
        return queryProxy;
    }
}

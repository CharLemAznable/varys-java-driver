package com.raiyee.varys;

import com.github.charlemaznable.lang.pool.PoolProxy;
import com.github.charlemaznable.lang.pool.PooledObjectCreator;
import com.raiyee.varys.api.Query;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class Varys {

    private Query queryProxy;

    public Varys(Config config) {
        GenericObjectPoolConfig<Query> queryPoolConfig
                = new GenericObjectPoolConfig<>();
        queryPoolConfig.setMaxTotal(config.getQueryPoolMaxTotal());
        queryPoolConfig.setMaxIdle(config.getQueryPoolMaxIdle());
        queryPoolConfig.setMinIdle(config.getQueryPoolMinIdle());
        this.queryProxy = PoolProxy.create(
                new PooledObjectCreator<Query>() {
                    @Override
                    public Query create() {
                        return new Query(config);
                    }
                }, queryPoolConfig);
    }

    public Query query() {
        return queryProxy;
    }
}

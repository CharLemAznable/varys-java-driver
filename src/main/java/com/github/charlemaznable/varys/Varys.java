package com.github.charlemaznable.varys;

import com.github.charlemaznable.lang.pool.PoolProxy;
import com.github.charlemaznable.lang.pool.PooledObjectCreator;
import com.github.charlemaznable.varys.api.Query;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.Synchronized;
import lombok.val;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import javax.annotation.Nonnull;

import static com.github.charlemaznable.lang.LoadingCachee.get;
import static com.github.charlemaznable.lang.LoadingCachee.simpleCache;

public class Varys {

    private static LoadingCache<Config, Varys> cache =
            simpleCache(new CacheLoader<Config, Varys>() {
                @Override
                public Varys load(@Nonnull Config config) {
                    return new Varys(config);
                }
            });
    private final Config config;
    private Query queryProxy;

    private Varys(Config config) {
        this.config = config;
    }

    public static Varys instance(Config config) {
        return get(cache, config);
    }

    @Synchronized
    public Query query() {
        if (null == queryProxy) {
            val queryPoolConfig = new GenericObjectPoolConfig<Query>();
            queryPoolConfig.setMaxTotal(config.getQueryPoolMaxTotal());
            queryPoolConfig.setMaxIdle(config.getQueryPoolMaxIdle());
            queryPoolConfig.setMinIdle(config.getQueryPoolMinIdle());
            queryProxy = PoolProxy.builder(new PooledObjectCreator<Query>() {})
                    .config(queryPoolConfig).args(config).build();
        }
        return queryProxy;
    }
}

package com.github.charlemaznable.varys.spring;

import com.github.charlemaznable.core.lang.pool.PoolProxy;
import com.github.charlemaznable.core.lang.pool.PooledObjectCreator;
import com.github.charlemaznable.core.miner.MinerFactory;
import com.github.charlemaznable.varys.api.Proxy;
import com.github.charlemaznable.varys.api.Query;
import com.github.charlemaznable.varys.config.VarysConfig;
import lombok.Synchronized;
import lombok.val;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Varys {

    @Autowired(required = false)
    private VarysConfig varysConfig;
    private Query queryProxy;
    private Proxy proxyProxy;

    @PostConstruct
    public void postConstruct() {
        if (null == varysConfig) {
            varysConfig = MinerFactory.getMiner(VarysConfig.class);
        }
    }

    @Synchronized
    public Query query() {
        if (null == queryProxy) {
            val queryPoolConfig = new GenericObjectPoolConfig<Query>();
            queryPoolConfig.setMaxTotal(varysConfig.queryPoolMaxTotal());
            queryPoolConfig.setMaxIdle(varysConfig.queryPoolMaxIdle());
            queryPoolConfig.setMinIdle(varysConfig.queryPoolMinIdle());
            queryProxy = PoolProxy.builder(new PooledObjectCreator<Query>() {})
                    .config(queryPoolConfig).args(varysConfig).build();
        }
        return queryProxy;
    }

    @Synchronized
    public Proxy proxy() {
        if (null == proxyProxy) {
            val proxyPoolConfig = new GenericObjectPoolConfig<Proxy>();
            proxyPoolConfig.setMaxTotal(varysConfig.proxyPoolMaxTotal());
            proxyPoolConfig.setMaxIdle(varysConfig.proxyPoolMaxIdle());
            proxyPoolConfig.setMinIdle(varysConfig.proxyPoolMinIdle());
            proxyProxy = PoolProxy.builder(new PooledObjectCreator<Proxy>() {})
                    .config(proxyPoolConfig).args(varysConfig).build();
        }
        return proxyProxy;
    }
}

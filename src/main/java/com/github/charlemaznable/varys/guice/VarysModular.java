package com.github.charlemaznable.varys.guice;

import com.github.charlemaznable.core.miner.MinerModular;
import com.github.charlemaznable.core.net.ohclient.OhModular;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.impl.Query;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.util.Providers;

import static com.github.charlemaznable.core.lang.Listt.newArrayList;
import static com.google.common.collect.Iterables.concat;

public final class VarysModular {

    private OhModular ohModular;

    public VarysModular() {
        this((VarysConfig) null);
    }

    public VarysModular(Class<? extends VarysConfig> configClass) {
        this(new MinerModular().createModule(configClass));
    }

    public VarysModular(VarysConfig configImpl) {
        this(new AbstractModule() {
            @Override
            protected void configure() {
                bind(VarysConfig.class).toProvider(Providers.of(configImpl));
            }
        });
    }

    public VarysModular(Module configModule) {
        this.ohModular = new OhModular(configModule);
    }

    public Module createModule(Class... otherClientClasses) {
        return createModule(newArrayList(otherClientClasses));
    }

    public Module createModule(Iterable<Class> otherClientClasses) {
        return this.ohModular.createModule(concat(
                newArrayList(Query.class), otherClientClasses));
    }

    public <T> T getClient(Class<T> ohClass) {
        return this.ohModular.getClient(ohClass);
    }
}

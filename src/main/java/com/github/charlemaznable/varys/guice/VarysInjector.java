package com.github.charlemaznable.varys.guice;

import com.github.charlemaznable.core.miner.MinerInjector;
import com.github.charlemaznable.core.net.ohclient.OhInjector;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.impl.Query;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import com.google.inject.util.Providers;

import static com.github.charlemaznable.core.lang.Listt.newArrayList;
import static com.google.common.collect.Iterables.concat;

public final class VarysInjector {

    private OhInjector ohInjector;

    public VarysInjector() {
        this(Modules.EMPTY_MODULE);
    }

    public VarysInjector(Class<? extends VarysConfig> configClass) {
        this(new MinerInjector().createModule(configClass));
    }

    public VarysInjector(VarysConfig configImpl) {
        this(new AbstractModule() {
            @Override
            protected void configure() {
                bind(VarysConfig.class).toProvider(Providers.of(configImpl));
            }
        });
    }

    public VarysInjector(Module configModule) {
        this.ohInjector = new OhInjector(configModule);
    }

    public Module createModule(Class... otherClientClasses) {
        return createModule(newArrayList(otherClientClasses));
    }

    public Module createModule(Iterable<Class> otherClientClasses) {
        return this.ohInjector.createModule(concat(
                newArrayList(Query.class), otherClientClasses));
    }

    public Injector createInjector(Class... otherClientClasses) {
        return createInjector(newArrayList(otherClientClasses));
    }

    public Injector createInjector(Iterable<Class> otherClientClasses) {
        return Guice.createInjector(createModule(otherClientClasses));
    }

    public <T> T getClient(Class<T> ohClass) {
        return this.ohInjector.getClient(ohClass);
    }
}

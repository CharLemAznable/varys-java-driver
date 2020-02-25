package com.github.charlemaznable.varys.guice;

import com.github.charlemaznable.core.miner.MinerInjector;
import com.github.charlemaznable.core.net.ohclient.OhInjector;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.impl.Query;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.util.Providers;

import static com.github.charlemaznable.core.lang.Listt.newArrayList;
import static com.google.common.collect.Iterables.concat;

public class VarysInjector extends OhInjector {

    public VarysInjector(Module... modules) {
        super(modules);
    }

    public VarysInjector(Iterable<? extends Module> modules) {
        super(modules);
    }

    public VarysInjector(Class<? extends VarysConfig> configClass,
                         Module... otherModules) {
        this(configClass, newArrayList(otherModules));
    }

    public VarysInjector(Class<? extends VarysConfig> configClass,
                         Iterable<? extends Module> otherModules) {
        this(new MinerInjector().createModule(configClass), otherModules);
    }

    public VarysInjector(VarysConfig configImpl,
                         Module... otherModules) {
        this(configImpl, newArrayList(otherModules));
    }

    public VarysInjector(VarysConfig configImpl,
                         Iterable<? extends Module> otherModules) {
        this(new AbstractModule() {
            @Override
            protected void configure() {
                bind(VarysConfig.class).toProvider(Providers.of(configImpl));
            }
        }, otherModules);
    }

    public VarysInjector(Module configModule,
                         Iterable<? extends Module> otherModules) {
        this(concat(newArrayList(configModule), otherModules));
    }

    @Override
    public Module createModule(Class... otherClientClasses) {
        return this.createModule(newArrayList(otherClientClasses));
    }

    @Override
    public Module createModule(Iterable<Class> otherClientClasses) {
        return super.createModule(concat(newArrayList(Query.class), otherClientClasses));
    }
}

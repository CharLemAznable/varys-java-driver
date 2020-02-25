package com.github.charlemaznable.varys.guice;

import com.github.charlemaznable.core.miner.MinerInjector;
import com.github.charlemaznable.core.net.ohclient.OhInjector;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.impl.Query;
import com.google.common.collect.Iterables;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Providers;

import java.util.Arrays;
import java.util.List;

import static com.github.charlemaznable.core.lang.Listt.newArrayList;

public final class VarysInjector {

    private OhInjector ohInjector;

    public VarysInjector(Module... otherModules) {
        this(Arrays.asList(otherModules));
    }

    public VarysInjector(Iterable<? extends Module> otherModules) {
        this(new MinerInjector().createModule(), otherModules);
    }

    public VarysInjector(Class<? extends VarysConfig> configClass,
                         Module... otherModules) {
        this(configClass, Arrays.asList(otherModules));
    }

    public VarysInjector(Class<? extends VarysConfig> configClass,
                         Iterable<? extends Module> otherModules) {
        this(new MinerInjector().createModule(configClass), otherModules);
    }

    public VarysInjector(VarysConfig configImpl,
                         Module... otherModules) {
        this(configImpl, Arrays.asList(otherModules));
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
        List<Module> baseModules = newArrayList(configModule);
        Iterables.addAll(baseModules, otherModules);
        this.ohInjector = new OhInjector(baseModules);
    }

    public Module createModule(Class... otherClientClasses) {
        return createModule(Arrays.asList(otherClientClasses));
    }

    public Module createModule(Iterable<Class> otherClientClasses) {
        List<Class> clientClasses = newArrayList(Query.class);
        Iterables.addAll(clientClasses, otherClientClasses);
        return this.ohInjector.createModule(clientClasses);
    }

    public Injector createInjector(Class... classes) {
        return createInjector(Arrays.asList(classes));
    }

    public Injector createInjector(Iterable<Class> classes) {
        return Guice.createInjector(createModule(classes));
    }

    public <T> T getClient(Class<T> ohClass) {
        return this.ohInjector.getClient(ohClass);
    }
}

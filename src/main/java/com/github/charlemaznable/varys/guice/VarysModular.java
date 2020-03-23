package com.github.charlemaznable.varys.guice;

import com.github.charlemaznable.core.miner.MinerModular;
import com.github.charlemaznable.core.net.ohclient.OhModular;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.impl.Query;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.util.Providers;

import static com.github.charlemaznable.core.lang.Listt.newArrayList;

public final class VarysModular {

    private OhModular ohModular;

    public VarysModular() {
        this((VarysConfig) null);
    }

    public VarysModular(Class<? extends VarysConfig> configClass) {
        this(new MinerModular().bindClasses(configClass).createModule());
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
        this.ohModular = new OhModular(configModule).bindClasses(Query.class);
    }

    public VarysModular bindOtherClients(Class<?>... otherClientClasses) {
        return bindOtherClients(newArrayList(otherClientClasses));
    }

    public VarysModular bindOtherClients(Iterable<Class<?>> otherClientClasses) {
        this.ohModular.bindClasses(otherClientClasses);
        return this;
    }

    public VarysModular scanOtherClientPackages(String... basePackages) {
        return scanOtherClientPackages(newArrayList(basePackages));
    }

    public VarysModular scanOtherClientPackages(Iterable<String> basePackages) {
        this.ohModular.scanPackages(basePackages);
        return this;
    }

    public VarysModular scanOtherClientPackageClasses(Class<?>... basePackageClasses) {
        return scanOtherClientPackageClasses(newArrayList(basePackageClasses));
    }

    public VarysModular scanOtherClientPackageClasses(Iterable<Class<?>> basePackageClasses) {
        this.ohModular.scanPackageClasses(basePackageClasses);
        return this;
    }

    public Module createModule() {
        return this.ohModular.createModule();
    }

    public <T> T getClient(Class<T> ohClass) {
        return this.ohModular.getClient(ohClass);
    }
}

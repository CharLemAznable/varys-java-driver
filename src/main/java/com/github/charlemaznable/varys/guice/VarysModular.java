package com.github.charlemaznable.varys.guice;

import com.github.charlemaznable.core.miner.MinerModular;
import com.github.charlemaznable.core.net.ohclient.OhClient;
import com.github.charlemaznable.core.net.ohclient.OhModular;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.impl.Query;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.util.Providers;
import lombok.val;
import org.springframework.util.ClassUtils;

import java.util.Set;
import java.util.function.Predicate;

import static com.github.charlemaznable.core.lang.Listt.newArrayList;
import static com.github.charlemaznable.core.spring.ClzResolver.getClasses;
import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Objects.nonNull;
import static org.springframework.core.annotation.AnnotationUtils.getAnnotation;

public final class VarysModular {

    private OhModular ohModular;
    private final Set<Class<?>> otherClientClasses = newHashSet();
    private final Predicate<Class<?>> resolverPredicate =
            clazz -> nonNull(getAnnotation(clazz, OhClient.class));

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
        this.ohModular = new OhModular(configModule);
    }

    public VarysModular bindOtherClients(Class<?>... otherClientClasses) {
        return bindOtherClients(newArrayList(otherClientClasses));
    }

    public VarysModular bindOtherClients(Iterable<Class<?>> otherClientClasses) {
        this.otherClientClasses.addAll(newArrayList(otherClientClasses));
        return this;
    }

    public VarysModular scanOtherClientPackages(String... basePackages) {
        return scanOtherClientPackages(newArrayList(basePackages));
    }

    public VarysModular scanOtherClientPackages(Iterable<String> basePackages) {
        for (val basePackage : basePackages) {
            bindOtherClients(getClasses(basePackage, resolverPredicate));
        }
        return this;
    }

    public VarysModular scanOtherClientPackageClasses(Class<?>... basePackageClasses) {
        return scanOtherClientPackageClasses(newArrayList(basePackageClasses));
    }

    public VarysModular scanOtherClientPackageClasses(Iterable<Class<?>> basePackageClasses) {
        for (val basePackageClass : basePackageClasses) {
            val basePackage = ClassUtils.getPackageName(basePackageClass);
            bindOtherClients(getClasses(basePackage, resolverPredicate));
        }
        return this;
    }

    public Module createModule() {
        return this.ohModular.bindClasses(concat(newArrayList(
                Query.class), otherClientClasses)).createModule();
    }

    public <T> T getClient(Class<T> ohClass) {
        return this.ohModular.getClient(ohClass);
    }
}

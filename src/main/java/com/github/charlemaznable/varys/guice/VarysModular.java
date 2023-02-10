package com.github.charlemaznable.varys.guice;

import com.github.charlemaznable.configservice.ConfigModular;
import com.github.charlemaznable.httpclient.ohclient.OhModular;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.impl.Query;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.util.Providers;

import javax.annotation.Nullable;

import static com.github.charlemaznable.core.lang.Listt.newArrayList;

public final class VarysModular {

    private final OhModular ohModular;

    public VarysModular() {
        this((VarysConfig) null);
    }

    public VarysModular(Class<? extends VarysConfig> configClass) {
        this(new ConfigModular().bindClasses(configClass).createModule());
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
        this.ohModular = new OhModular(configModule, new AbstractModule() {

            @Provides
            public VarysConfig.ProxyWechatAppConfig proxyWechatAppConfig(@Nullable VarysConfig varysConfig) {
                return new VarysConfig.ProxyWechatAppConfig(varysConfig);
            }

            @Provides
            public VarysConfig.ProxyWechatCorpConfig proxyWechatCorpConfig(@Nullable VarysConfig varysConfig) {
                return new VarysConfig.ProxyWechatCorpConfig(varysConfig);
            }

            @Provides
            public VarysConfig.ProxyWechatTpConfig proxyWechatTpConfig(@Nullable VarysConfig varysConfig) {
                return new VarysConfig.ProxyWechatTpConfig(varysConfig);
            }

            @Provides
            public VarysConfig.ProxyWechatTpAuthConfig proxyWechatTpAuthConfig(@Nullable VarysConfig varysConfig) {
                return new VarysConfig.ProxyWechatTpAuthConfig(varysConfig);
            }

            @Provides
            public VarysConfig.ProxyFengniaoAppConfig proxyFengniaoAppConfig(@Nullable VarysConfig varysConfig) {
                return new VarysConfig.ProxyFengniaoAppConfig(varysConfig);
            }

            @Provides
            public VarysConfig.ProxyShansongAppDeveloperConfig proxyShansongAppDeveloperConfig(@Nullable VarysConfig varysConfig) {
                return new VarysConfig.ProxyShansongAppDeveloperConfig(varysConfig);
            }

            @Provides
            public VarysConfig.ProxyShansongAppFileConfig proxyShansongAppFileConfig(@Nullable VarysConfig varysConfig) {
                return new VarysConfig.ProxyShansongAppFileConfig(varysConfig);
            }

            @Provides
            public VarysConfig.ProxyShansongAppMerchantConfig proxyShansongAppMerchantConfig(@Nullable VarysConfig varysConfig) {
                return new VarysConfig.ProxyShansongAppMerchantConfig(varysConfig);
            }
        }).bindClasses(Query.class);
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

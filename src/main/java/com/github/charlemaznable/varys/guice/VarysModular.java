package com.github.charlemaznable.varys.guice;

import com.github.charlemaznable.configservice.ConfigModular;
import com.github.charlemaznable.httpclient.ohclient.OhModular;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varys.impl.VarysCallTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysConnectTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysProxyFengniaoAppUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyShansongAppDeveloperUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyShansongAppFileUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyShansongAppMerchantUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatAppUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatCorpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatTpAuthUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatTpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysQueryUrlProvider;
import com.github.charlemaznable.varys.impl.VarysReadTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysWriteTimeoutProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.util.Providers;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Nullable;

import static com.github.charlemaznable.configservice.ConfigFactory.getConfig;
import static com.github.charlemaznable.core.lang.Condition.nullThen;
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

            @AllArgsConstructor
            @Getter
            static class VarysConfigSupplier {

                private final VarysConfig varysConfig;
            }

            @Provides
            @Singleton
            public VarysConfigSupplier varysConfigSupplier(@Nullable VarysConfig varysConfig) {
                return new VarysConfigSupplier(nullThen(varysConfig, () -> getConfig(VarysConfig.class)));
            }

            @Provides
            public VarysQueryUrlProvider varysQueryUrlProvider(VarysConfigSupplier supplier) {
                return new VarysQueryUrlProvider(supplier.getVarysConfig());
            }

            @Provides
            public VarysCallTimeoutProvider varysCallTimeoutProvider(VarysConfigSupplier supplier) {
                return new VarysCallTimeoutProvider(supplier.getVarysConfig());
            }

            @Provides
            public VarysConnectTimeoutProvider varysConnectTimeoutProvider(VarysConfigSupplier supplier) {
                return new VarysConnectTimeoutProvider(supplier.getVarysConfig());
            }

            @Provides
            public VarysReadTimeoutProvider varysReadTimeoutProvider(VarysConfigSupplier supplier) {
                return new VarysReadTimeoutProvider(supplier.getVarysConfig());
            }

            @Provides
            public VarysWriteTimeoutProvider varysWriteTimeoutProvider(VarysConfigSupplier supplier) {
                return new VarysWriteTimeoutProvider(supplier.getVarysConfig());
            }

            @Provides
            public VarysProxyWechatAppUrlProvider varysProxyWechatAppUrlProvider(VarysConfigSupplier supplier) {
                return new VarysProxyWechatAppUrlProvider(supplier.getVarysConfig());
            }

            @Provides
            public VarysProxyWechatCorpUrlProvider varysProxyWechatCorpUrlProvider(VarysConfigSupplier supplier) {
                return new VarysProxyWechatCorpUrlProvider(supplier.getVarysConfig());
            }

            @Provides
            public VarysProxyWechatTpUrlProvider varysProxyWechatTpUrlProvider(VarysConfigSupplier supplier) {
                return new VarysProxyWechatTpUrlProvider(supplier.getVarysConfig());
            }

            @Provides
            public VarysProxyWechatTpAuthUrlProvider varysProxyWechatTpAuthUrlProvider(VarysConfigSupplier supplier) {
                return new VarysProxyWechatTpAuthUrlProvider(supplier.getVarysConfig());
            }

            @Provides
            public VarysProxyFengniaoAppUrlProvider varysProxyFengniaoAppUrlProvider(VarysConfigSupplier supplier) {
                return new VarysProxyFengniaoAppUrlProvider(supplier.getVarysConfig());
            }

            @Provides
            public VarysProxyShansongAppDeveloperUrlProvider varysProxyShansongAppDeveloperUrlProvider(VarysConfigSupplier supplier) {
                return new VarysProxyShansongAppDeveloperUrlProvider(supplier.getVarysConfig());
            }

            @Provides
            public VarysProxyShansongAppFileUrlProvider varysProxyShansongAppFileUrlProvider(VarysConfigSupplier supplier) {
                return new VarysProxyShansongAppFileUrlProvider(supplier.getVarysConfig());
            }

            @Provides
            public VarysProxyShansongAppMerchantUrlProvider varysProxyShansongAppMerchantUrlProvider(VarysConfigSupplier supplier) {
                return new VarysProxyShansongAppMerchantUrlProvider(supplier.getVarysConfig());
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

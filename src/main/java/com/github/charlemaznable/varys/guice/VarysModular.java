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
import com.google.inject.util.Providers;

import static com.github.charlemaznable.core.lang.Listt.newArrayList;

public final class VarysModular {

    private final OhModular ohModular;

    public VarysModular() {
        this(VarysConfig.class);
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
            public VarysQueryUrlProvider varysQueryUrlProvider(VarysConfig varysConfig) {
                return new VarysQueryUrlProvider(varysConfig);
            }
            @Provides
            public VarysCallTimeoutProvider varysCallTimeoutProvider(VarysConfig varysConfig) {
                return new VarysCallTimeoutProvider(varysConfig);
            }
            @Provides
            public VarysConnectTimeoutProvider varysConnectTimeoutProvider(VarysConfig varysConfig) {
                return new VarysConnectTimeoutProvider(varysConfig);
            }
            @Provides
            public VarysReadTimeoutProvider varysReadTimeoutProvider(VarysConfig varysConfig) {
                return new VarysReadTimeoutProvider(varysConfig);
            }
            @Provides
            public VarysWriteTimeoutProvider varysWriteTimeoutProvider(VarysConfig varysConfig) {
                return new VarysWriteTimeoutProvider(varysConfig);
            }
            @Provides
            public VarysProxyWechatAppUrlProvider varysProxyWechatAppUrlProvider(VarysConfig varysConfig) {
                return new VarysProxyWechatAppUrlProvider(varysConfig);
            }
            @Provides
            public VarysProxyWechatCorpUrlProvider varysProxyWechatCorpUrlProvider(VarysConfig varysConfig) {
                return new VarysProxyWechatCorpUrlProvider(varysConfig);
            }
            @Provides
            public VarysProxyWechatTpUrlProvider varysProxyWechatTpUrlProvider(VarysConfig varysConfig) {
                return new VarysProxyWechatTpUrlProvider(varysConfig);
            }
            @Provides
            public VarysProxyWechatTpAuthUrlProvider varysProxyWechatTpAuthUrlProvider(VarysConfig varysConfig) {
                return new VarysProxyWechatTpAuthUrlProvider(varysConfig);
            }
            @Provides
            public VarysProxyFengniaoAppUrlProvider varysProxyFengniaoAppUrlProvider(VarysConfig varysConfig) {
                return new VarysProxyFengniaoAppUrlProvider(varysConfig);
            }
            @Provides
            public VarysProxyShansongAppDeveloperUrlProvider varysProxyShansongAppDeveloperUrlProvider(VarysConfig varysConfig) {
                return new VarysProxyShansongAppDeveloperUrlProvider(varysConfig);
            }
            @Provides
            public VarysProxyShansongAppFileUrlProvider varysProxyShansongAppFileUrlProvider(VarysConfig varysConfig) {
                return new VarysProxyShansongAppFileUrlProvider(varysConfig);
            }
            @Provides
            public VarysProxyShansongAppMerchantUrlProvider varysProxyShansongAppMerchantUrlProvider(VarysConfig varysConfig) {
                return new VarysProxyShansongAppMerchantUrlProvider(varysConfig);
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

package com.github.charlemaznable.varys.spring;

import com.github.charlemaznable.core.spring.ElvesImport;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import static com.github.charlemaznable.httpclient.ohclient.OhFactory.springOhLoader;

@Configuration
@ElvesImport
public class VarysConfigurer {

    @Bean("com.github.charlemaznable.varys.impl.Query")
    public Query query() {
        return springOhLoader().getClient(Query.class);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysQueryUrlProvider")
    public VarysQueryUrlProvider varysQueryUrlProvider(@Nullable VarysConfig varysConfig) {
        return new VarysQueryUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysCallTimeoutProvider")
    public VarysCallTimeoutProvider varysCallTimeoutProvider(@Nullable VarysConfig varysConfig) {
        return new VarysCallTimeoutProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysConnectTimeoutProvider")
    public VarysConnectTimeoutProvider varysConnectTimeoutProvider(@Nullable VarysConfig varysConfig) {
        return new VarysConnectTimeoutProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysReadTimeoutProvider")
    public VarysReadTimeoutProvider varysReadTimeoutProvider(@Nullable VarysConfig varysConfig) {
        return new VarysReadTimeoutProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysWriteTimeoutProvider")
    public VarysWriteTimeoutProvider varysWriteTimeoutProvider(@Nullable VarysConfig varysConfig) {
        return new VarysWriteTimeoutProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyWechatAppUrlProvider")
    public VarysProxyWechatAppUrlProvider varysProxyWechatAppUrlProvider(@Nullable VarysConfig varysConfig) {
        return new VarysProxyWechatAppUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyWechatCorpUrlProvider")
    public VarysProxyWechatCorpUrlProvider varysProxyWechatCorpUrlProvider(@Nullable VarysConfig varysConfig) {
        return new VarysProxyWechatCorpUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyWechatTpUrlProvider")
    public VarysProxyWechatTpUrlProvider varysProxyWechatTpUrlProvider(@Nullable VarysConfig varysConfig) {
        return new VarysProxyWechatTpUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyWechatTpAuthUrlProvider")
    public VarysProxyWechatTpAuthUrlProvider varysProxyWechatTpAuthUrlProvider(@Nullable VarysConfig varysConfig) {
        return new VarysProxyWechatTpAuthUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyFengniaoAppUrlProvider")
    public VarysProxyFengniaoAppUrlProvider varysProxyFengniaoAppUrlProvider(@Nullable VarysConfig varysConfig) {
        return new VarysProxyFengniaoAppUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyShansongAppDeveloperUrlProvider")
    public VarysProxyShansongAppDeveloperUrlProvider varysProxyShansongAppDeveloperUrlProvider(@Nullable VarysConfig varysConfig) {
        return new VarysProxyShansongAppDeveloperUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyShansongAppFileUrlProvider")
    public VarysProxyShansongAppFileUrlProvider varysProxyShansongAppFileUrlProvider(@Nullable VarysConfig varysConfig) {
        return new VarysProxyShansongAppFileUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyShansongAppMerchantUrlProvider")
    public VarysProxyShansongAppMerchantUrlProvider varysProxyShansongAppMerchantUrlProvider(@Nullable VarysConfig varysConfig) {
        return new VarysProxyShansongAppMerchantUrlProvider(varysConfig);
    }
}

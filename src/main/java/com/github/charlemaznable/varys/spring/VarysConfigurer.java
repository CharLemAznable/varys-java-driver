package com.github.charlemaznable.varys.spring;

import com.github.charlemaznable.core.spring.ElvesImport;
import com.github.charlemaznable.httpclient.ohclient.OhScan;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import static com.github.charlemaznable.configservice.ConfigFactory.getConfig;
import static com.github.charlemaznable.core.lang.Condition.nullThen;

@Configuration
@ElvesImport
@OhScan(basePackageClasses = Query.class)
public class VarysConfigurer {

    private final VarysConfig varysConfig;

    @Autowired
    public VarysConfigurer(@Nullable VarysConfig varysConfig) {
        this.varysConfig = nullThen(varysConfig, () -> getConfig(VarysConfig.class));
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysQueryUrlProvider")
    public VarysQueryUrlProvider varysQueryUrlProvider() {
        return new VarysQueryUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysCallTimeoutProvider")
    public VarysCallTimeoutProvider varysCallTimeoutProvider() {
        return new VarysCallTimeoutProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysConnectTimeoutProvider")
    public VarysConnectTimeoutProvider varysConnectTimeoutProvider() {
        return new VarysConnectTimeoutProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysReadTimeoutProvider")
    public VarysReadTimeoutProvider varysReadTimeoutProvider() {
        return new VarysReadTimeoutProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysWriteTimeoutProvider")
    public VarysWriteTimeoutProvider varysWriteTimeoutProvider() {
        return new VarysWriteTimeoutProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyWechatAppUrlProvider")
    public VarysProxyWechatAppUrlProvider varysProxyWechatAppUrlProvider() {
        return new VarysProxyWechatAppUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyWechatCorpUrlProvider")
    public VarysProxyWechatCorpUrlProvider varysProxyWechatCorpUrlProvider() {
        return new VarysProxyWechatCorpUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyWechatTpUrlProvider")
    public VarysProxyWechatTpUrlProvider varysProxyWechatTpUrlProvider() {
        return new VarysProxyWechatTpUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyWechatTpAuthUrlProvider")
    public VarysProxyWechatTpAuthUrlProvider varysProxyWechatTpAuthUrlProvider() {
        return new VarysProxyWechatTpAuthUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyFengniaoAppUrlProvider")
    public VarysProxyFengniaoAppUrlProvider varysProxyFengniaoAppUrlProvider() {
        return new VarysProxyFengniaoAppUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyShansongAppDeveloperUrlProvider")
    public VarysProxyShansongAppDeveloperUrlProvider varysProxyShansongAppDeveloperUrlProvider() {
        return new VarysProxyShansongAppDeveloperUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyShansongAppFileUrlProvider")
    public VarysProxyShansongAppFileUrlProvider varysProxyShansongAppFileUrlProvider() {
        return new VarysProxyShansongAppFileUrlProvider(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.impl.VarysProxyShansongAppMerchantUrlProvider")
    public VarysProxyShansongAppMerchantUrlProvider varysProxyShansongAppMerchantUrlProvider() {
        return new VarysProxyShansongAppMerchantUrlProvider(varysConfig);
    }
}

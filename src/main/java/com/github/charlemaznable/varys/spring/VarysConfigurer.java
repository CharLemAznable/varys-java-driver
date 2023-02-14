package com.github.charlemaznable.varys.spring;

import com.github.charlemaznable.core.spring.ElvesImport;
import com.github.charlemaznable.httpclient.ohclient.OhScannerRegistrar;
import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.impl.Query;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import static com.github.charlemaznable.httpclient.ohclient.OhFactory.springOhLoader;

@Configuration
@ElvesImport
public class VarysConfigurer {

    @Bean("com.github.charlemaznable.varys.impl.Query")
    public OhScannerRegistrar.OhClientFactoryBean query() {
        val factoryBean = new OhScannerRegistrar.OhClientFactoryBean();
        factoryBean.setXyzInterface(Query.class);
        factoryBean.setOhLoader(springOhLoader());
        return factoryBean;
    }

    @Bean("com.github.charlemaznable.varys.config.VarysConfig.ProxyWechatAppConfig")
    public VarysConfig.ProxyWechatAppConfig proxyWechatAppConfig(@Nullable VarysConfig varysConfig) {
        return new VarysConfig.ProxyWechatAppConfig(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.config.VarysConfig.ProxyWechatCorpConfig")
    public VarysConfig.ProxyWechatCorpConfig proxyWechatCorpConfig(@Nullable VarysConfig varysConfig) {
        return new VarysConfig.ProxyWechatCorpConfig(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.config.VarysConfig.ProxyWechatTpConfig")
    public VarysConfig.ProxyWechatTpConfig proxyWechatTpConfig(@Nullable VarysConfig varysConfig) {
        return new VarysConfig.ProxyWechatTpConfig(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.config.VarysConfig.ProxyWechatTpAuthConfig")
    public VarysConfig.ProxyWechatTpAuthConfig proxyWechatTpAuthConfig(@Nullable VarysConfig varysConfig) {
        return new VarysConfig.ProxyWechatTpAuthConfig(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.config.VarysConfig.ProxyFengniaoAppConfig")
    public VarysConfig.ProxyFengniaoAppConfig proxyFengniaoAppConfig(@Nullable VarysConfig varysConfig) {
        return new VarysConfig.ProxyFengniaoAppConfig(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.config.VarysConfig.ProxyShansongAppDeveloperConfig")
    public VarysConfig.ProxyShansongAppDeveloperConfig proxyShansongAppDeveloperConfig(@Nullable VarysConfig varysConfig) {
        return new VarysConfig.ProxyShansongAppDeveloperConfig(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.config.VarysConfig.ProxyShansongAppFileConfig")
    public VarysConfig.ProxyShansongAppFileConfig proxyShansongAppFileConfig(@Nullable VarysConfig varysConfig) {
        return new VarysConfig.ProxyShansongAppFileConfig(varysConfig);
    }

    @Bean("com.github.charlemaznable.varys.config.VarysConfig.ProxyShansongAppMerchantConfig")
    public VarysConfig.ProxyShansongAppMerchantConfig proxyShansongAppMerchantConfig(@Nullable VarysConfig varysConfig) {
        return new VarysConfig.ProxyShansongAppMerchantConfig(varysConfig);
    }
}

package com.github.charlemaznable.varys.config;

import com.github.charlemaznable.configservice.Config;
import com.github.charlemaznable.httpclient.configurer.MappingConfigurer;
import com.github.charlemaznable.httpclient.configurer.configservice.MappingConfig;
import com.github.charlemaznable.httpclient.ohclient.configurer.configservice.ClientTimeoutConfig;
import lombok.experimental.Delegate;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.charlemaznable.configservice.ConfigFactory.getConfig;
import static com.github.charlemaznable.core.lang.Condition.checkNotNull;
import static com.github.charlemaznable.core.lang.Condition.nullThen;
import static com.github.charlemaznable.core.lang.Listt.newArrayList;
import static org.apache.commons.lang3.StringUtils.appendIfMissing;

@Config(keyset = "Varys", key = "${varys-config:-default}")
public interface VarysConfig extends MappingConfig, ClientTimeoutConfig {

    String address();

    default String urlsString() {
        return checkNotNull(address());
    }

    abstract class ProxyConfig implements MappingConfig, ClientTimeoutConfig {

        @Delegate(excludes = MappingConfigurer.class)
        private final VarysConfig varysConfig;

        public ProxyConfig(@Nullable VarysConfig varysConfig) {
            this.varysConfig = nullThen(varysConfig, () -> getConfig(VarysConfig.class));
        }

        public abstract String subpath();

        @Override
        public List<String> urls() {
            return newArrayList(MappingConfig.super.urls()).stream()
                    .map(s -> appendIfMissing(s, "/") + subpath())
                    .collect(Collectors.toList());
        }
    }

    class ProxyWechatAppConfig extends ProxyConfig {

        public ProxyWechatAppConfig(@Nullable VarysConfig varysConfig) {
            super(varysConfig);
        }

        @Override
        public String subpath() {
            return "proxy-wechat-app";
        }
    }

    class ProxyWechatCorpConfig extends ProxyConfig {

        public ProxyWechatCorpConfig(@Nullable VarysConfig varysConfig) {
            super(varysConfig);
        }

        @Override
        public String subpath() {
            return "proxy-wechat-corp";
        }
    }

    class ProxyWechatTpConfig extends ProxyConfig {

        public ProxyWechatTpConfig(@Nullable VarysConfig varysConfig) {
            super(varysConfig);
        }

        @Override
        public String subpath() {
            return "proxy-wechat-tp";
        }
    }

    class ProxyWechatTpAuthConfig extends ProxyConfig {

        public ProxyWechatTpAuthConfig(@Nullable VarysConfig varysConfig) {
            super(varysConfig);
        }

        @Override
        public String subpath() {
            return "proxy-wechat-tp-auth";
        }
    }

    class ProxyFengniaoAppConfig extends ProxyConfig {

        public ProxyFengniaoAppConfig(@Nullable VarysConfig varysConfig) {
            super(varysConfig);
        }

        @Override
        public String subpath() {
            return "proxy-fengniao-app";
        }
    }

    class ProxyShansongAppDeveloperConfig extends ProxyConfig {

        public ProxyShansongAppDeveloperConfig(@Nullable VarysConfig varysConfig) {
            super(varysConfig);
        }

        @Override
        public String subpath() {
            return "proxy-shansong-app-developer";
        }
    }

    class ProxyShansongAppFileConfig extends ProxyConfig {

        public ProxyShansongAppFileConfig(@Nullable VarysConfig varysConfig) {
            super(varysConfig);
        }

        @Override
        public String subpath() {
            return "proxy-shansong-app-file";
        }
    }

    class ProxyShansongAppMerchantConfig extends ProxyConfig {

        public ProxyShansongAppMerchantConfig(@Nullable VarysConfig varysConfig) {
            super(varysConfig);
        }

        @Override
        public String subpath() {
            return "proxy-shansong-app-merchant";
        }
    }
}

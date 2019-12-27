package com.github.charlemaznable.varys.api;

import com.github.charlemaznable.core.net.HttpReq;
import com.github.charlemaznable.varys.config.VarysConfig;
import lombok.AllArgsConstructor;

import java.util.Map;

import static com.github.charlemaznable.core.lang.Condition.checkNotNull;
import static java.lang.String.format;

@AllArgsConstructor
public class Proxy {

    private final VarysConfig varysConfig;

    public ProxyReq wechatApp(String codeName, String proxyPath) {
        return new ProxyReq(checkNotNull(varysConfig.address())
                + varysConfig.proxyWechatAppPath() + codeName + fixProxyPath(proxyPath));
    }

    public ProxyReq wechatApp(String codeName, String proxyPathTemplate, Object... proxyPathArgs) {
        return new ProxyReq(checkNotNull(varysConfig.address())
                + varysConfig.proxyWechatAppPath() + codeName + fixProxyPath(format(proxyPathTemplate, proxyPathArgs)));
    }

    public ProxyReq wechatCorp(String codeName, String proxyPath) {
        return new ProxyReq(checkNotNull(varysConfig.address())
                + varysConfig.proxyWechatCorpPath() + codeName + fixProxyPath(proxyPath));
    }

    public ProxyReq wechatCorp(String codeName, String proxyPathTemplate, Object... proxyPathArgs) {
        return new ProxyReq(checkNotNull(varysConfig.address())
                + varysConfig.proxyWechatCorpPath() + codeName + fixProxyPath(format(proxyPathTemplate, proxyPathArgs)));
    }

    public class ProxyReq {

        private HttpReq httpReq;

        private ProxyReq(String baseUrl) {
            httpReq = new HttpReq(baseUrl);
        }

        public ProxyReq prop(String name, String value) {
            httpReq.prop(name, value);
            return this;
        }

        public ProxyReq param(String name, String value) {
            httpReq.param(name, value);
            return this;
        }

        public ProxyReq params(Map<String, String> params) {
            httpReq.params(params);
            return this;
        }

        public ProxyReq requestBody(String requestBody) {
            httpReq.requestBody(requestBody);
            return this;
        }

        public String get() {
            return httpReq.get();
        }

        public String post() {
            return httpReq.post();
        }
    }

    private static String fixProxyPath(String proxyPath) {
        return proxyPath.startsWith("/") ? proxyPath : "/" + proxyPath;
    }
}

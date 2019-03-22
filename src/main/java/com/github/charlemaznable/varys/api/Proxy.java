package com.github.charlemaznable.varys.api;

import com.github.charlemaznable.net.HttpReq;
import com.github.charlemaznable.varys.Config;
import lombok.AllArgsConstructor;

import java.util.Map;

import static java.lang.String.format;

@AllArgsConstructor
public class Proxy {

    private final static String proxyWechatAppPath = "/proxy-wechat-app/";
    private final static String proxyWechatCorpPath = "/proxy-wechat-corp/";

    private final Config config;

    public ProxyReq wechatApp(String codeName, String proxyPath) {
        return new ProxyReq(config.getAddress() + proxyWechatAppPath
                + codeName + fixProxyPath(proxyPath));
    }

    public ProxyReq wechatApp(String codeName, String proxyPathTemplate, Object... proxyPathArgs) {
        return new ProxyReq(config.getAddress() + proxyWechatAppPath
                + codeName + fixProxyPath(format(proxyPathTemplate, proxyPathArgs)));
    }

    public ProxyReq wechatCorp(String codeName, String proxyPath) {
        return new ProxyReq(config.getAddress() + proxyWechatCorpPath
                + codeName + fixProxyPath(proxyPath));
    }

    public ProxyReq wechatCorp(String codeName, String proxyPathTemplate, Object... proxyPathArgs) {
        return new ProxyReq(config.getAddress() + proxyWechatCorpPath
                + codeName + fixProxyPath(format(proxyPathTemplate, proxyPathArgs)));
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

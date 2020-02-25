package com.github.charlemaznable.varystest.guice.instanceconfig;

import com.github.charlemaznable.varys.guice.VarysInjector;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyError;
import com.google.inject.Injector;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.github.charlemaznable.core.codec.Json.jsonOf;
import static com.github.charlemaznable.varystest.mock.InstanceConfigMock.proxyInstanceConfig;
import static com.github.charlemaznable.varystest.mock.InstanceConfigMock.queryInstanceConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstanceConfigTest {

    private static VarysInjector varysInjector;
    private static Injector injector;

    @BeforeAll
    public static void beforeAll() {
        varysInjector = new VarysInjector(new InstanceConfig());
        injector = varysInjector.createInjector(
                ProxyAppDemo.class, ProxyCorpDemo.class, ProxyError.class);
    }

    @SneakyThrows
    @Test
    public void testInstanceConfigQuery() {
        queryInstanceConfig(() -> {
            val query = varysInjector.getClient(Query.class);

            val appTokenResp = query.appToken("instance");
            assertEquals("1000", appTokenResp.getAppId());
            assertEquals("instanceToken", appTokenResp.getToken());

            val appAuthorizerTokenResp = query.appAuthorizerToken("instance", "abcd");
            assertEquals("1000", appAuthorizerTokenResp.getAppId());
            assertEquals("abcd", appAuthorizerTokenResp.getAuthorizerAppId());
            assertEquals("instanceToken", appAuthorizerTokenResp.getToken());

            val corpTokenResp = query.corpToken("instance");
            assertEquals("10000", corpTokenResp.getCorpId());
            assertEquals("instanceToken", corpTokenResp.getToken());

            val corpAuthorizerTokenResp = query.corpAuthorizerToken("instance", "xyz");
            assertEquals("10000", corpAuthorizerTokenResp.getCorpId());
            assertEquals("xyz", corpAuthorizerTokenResp.getSuiteId());
            assertEquals("instanceToken", corpAuthorizerTokenResp.getToken());
        });
    }

    @SneakyThrows
    @Test
    public void testInstanceConfigProxy() {
        proxyInstanceConfig(() -> {
            val proxyApp = injector.getInstance(ProxyAppDemo.class);
            val proxyCorp = injector.getInstance(ProxyCorpDemo.class);

            val wechatAppResp = proxyApp.wechatApp("instance", "b");
            assertEquals("instanceWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyApp.wechatAppParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceWechatAppParamResp", wechatAppParamResp);

            val wechatCorpResp = proxyCorp.wechatCorp("instance", "b");
            assertEquals("instanceWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyCorp.wechatCorpParam("instance", "testParam", "b");
            assertEquals("instanceWechatCorpParamResp", wechatCorpParamResp);
        });
    }
}

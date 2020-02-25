package com.github.charlemaznable.varystest.guice.defaultconfig;

import com.github.charlemaznable.varys.guice.VarysInjector;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyError;
import com.google.inject.Injector;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import static com.github.charlemaznable.core.codec.Json.jsonOf;
import static com.github.charlemaznable.varystest.mock.DefaultConfigMock.proxyDefaultConfig;
import static com.github.charlemaznable.varystest.mock.DefaultConfigMock.queryDefaultConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultConfigTest {

    private static VarysInjector varysInjector;
    private static Injector injector;

    @BeforeAll
    public static void beforeAll() {
        varysInjector = new VarysInjector();
        injector = varysInjector.createInjector(
                ProxyAppDemo.class, ProxyCorpDemo.class, ProxyError.class);
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("Varys", "default",
                "address=http://127.0.0.1:4236/varys\n");
    }

    @AfterAll
    public static void afterAll() {
        MockDiamondServer.tearDownMockServer();
    }

    @SneakyThrows
    @Test
    public void testDefaultConfigQuery() {
        queryDefaultConfig(() -> {
            val query = injector.getInstance(Query.class);

            val appTokenResp = query.appToken("default");
            assertEquals("1000", appTokenResp.getAppId());
            assertEquals("defaultToken", appTokenResp.getToken());

            val appAuthorizerTokenResp = query.appAuthorizerToken("default", "abcd");
            assertEquals("1000", appAuthorizerTokenResp.getAppId());
            assertEquals("abcd", appAuthorizerTokenResp.getAuthorizerAppId());
            assertEquals("defaultToken", appAuthorizerTokenResp.getToken());

            val corpTokenResp = query.corpToken("default");
            assertEquals("10000", corpTokenResp.getCorpId());
            assertEquals("defaultToken", corpTokenResp.getToken());

            val corpAuthorizerTokenResp = query.corpAuthorizerToken("default", "xyz");
            assertEquals("10000", corpAuthorizerTokenResp.getCorpId());
            assertEquals("xyz", corpAuthorizerTokenResp.getSuiteId());
            assertEquals("defaultToken", corpAuthorizerTokenResp.getToken());
        });
    }

    @SneakyThrows
    @Test
    public void testDefaultConfigProxy() {
        proxyDefaultConfig(() -> {
            val proxyApp = injector.getInstance(ProxyAppDemo.class);
            val proxyCorp = injector.getInstance(ProxyCorpDemo.class);

            val wechatAppResp = proxyApp.wechatApp("default", "b");
            assertEquals("defaultWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyApp.wechatAppParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatAppParamResp", wechatAppParamResp);

            val wechatCorpResp = proxyCorp.wechatCorp("default", "b");
            assertEquals("defaultWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyCorp.wechatCorpParam("default", "testParam", "b");
            assertEquals("defaultWechatCorpParamResp", wechatCorpParamResp);
        });
    }

    @Test
    public void testProxyError() {
        val proxyErrorInstance = injector.getInstance(ProxyError.class);
        try {
            proxyErrorInstance.proxyError("test");
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof IllegalArgumentException);
        }
        val proxyErrorClient = varysInjector.getClient(ProxyError.class);
        assertThrows(IllegalArgumentException.class,
                () -> proxyErrorClient.proxyError("test"));
    }
}

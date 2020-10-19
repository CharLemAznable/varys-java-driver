package com.github.charlemaznable.varystest.guice.defaultconfig;

import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.guice.VarysModular;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varys.impl.VarysCallTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysConnectTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysPathProvider;
import com.github.charlemaznable.varys.impl.VarysProxyAppUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyCorpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyMpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysQueryUrlProvider;
import com.github.charlemaznable.varys.impl.VarysReadTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysWriteTimeoutProvider;
import com.github.charlemaznable.varystest.proxy.ProxyAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyError;
import com.github.charlemaznable.varystest.proxy.ProxyMpDemo;
import com.google.inject.Guice;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultConfigTest {

    private static VarysModular varysModular;
    private static Injector injector;

    @BeforeAll
    public static void beforeAll() {
        varysModular = new VarysModular();
        injector = Guice.createInjector(varysModular.bindOtherClients(
                ProxyAppDemo.class, ProxyMpDemo.class, ProxyCorpDemo.class,
                ProxyError.class).createModule());
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
            val query = varysModular.getClient(Query.class);

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

            val toutiaoAppTokenResp = query.toutiaoAppToken("default");
            assertEquals("2000", toutiaoAppTokenResp.getAppId());
            assertEquals("defaultToken", toutiaoAppTokenResp.getToken());

            val wechatMpLoginResp = query.wechatMpLogin("default", "JSCODE");
            assertEquals("openid", wechatMpLoginResp.getOpenId());
            assertEquals("session_key", wechatMpLoginResp.getSessionKey());
            assertEquals("unionid", wechatMpLoginResp.getUnionId());
            assertEquals(0, wechatMpLoginResp.getErrcode());
            assertEquals("OK", wechatMpLoginResp.getErrmsg());
        });
    }

    @SneakyThrows
    @Test
    public void testDefaultConfigProxy() {
        proxyDefaultConfig(() -> {
            val proxyApp = injector.getInstance(ProxyAppDemo.class);
            val proxyMp = injector.getInstance(ProxyMpDemo.class);
            val proxyCorp = injector.getInstance(ProxyCorpDemo.class);

            val wechatAppResp = proxyApp.wechatApp("default", "b");
            assertEquals("defaultWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyApp.wechatAppParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatAppParamResp", wechatAppParamResp);

            val wechatMpResp = proxyMp.wechatMp("default", "b");
            assertEquals("defaultWechatMpResp", wechatMpResp);

            val wechatMpParamResp = proxyMp.wechatMpParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatMpParamResp", wechatMpParamResp);

            val wechatCorpResp = proxyCorp.wechatCorp("default", "b");
            assertEquals("defaultWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyCorp.wechatCorpParam("default", "testParam", "b");
            assertEquals("defaultWechatCorpParamResp", wechatCorpParamResp);
        });
    }

    @Test
    public void testProxyError() {
        assertNull(injector.getInstance(VarysConfig.class));
        assertNotNull(injector.getInstance(VarysQueryUrlProvider.class));
        try {
            injector.getInstance(ProxyError.class);
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof IllegalArgumentException);
        }
        assertThrows(IllegalArgumentException.class, () ->
                varysModular.getClient(ProxyError.class));
    }

    @Test
    public void testContext() {
        assertNull(injector.getInstance(VarysConfig.class));

        val varysPathProvider = injector
                .getInstance(VarysPathProvider.class);
        assertNotNull(varysPathProvider);
        assertEquals(new VarysPathProvider().value(Query.class, "queryWechatAppToken"),
                varysPathProvider.value(Query.class, "queryWechatAppToken"));

        val varysProxyAppUrlProvider = injector
                .getInstance(VarysProxyAppUrlProvider.class);
        assertNotNull(varysProxyAppUrlProvider);
        assertEquals(new VarysProxyAppUrlProvider().url(Query.class),
                varysProxyAppUrlProvider.url(Query.class));

        val varysProxyMpUrlProvider = injector
                .getInstance(VarysProxyMpUrlProvider.class);
        assertNotNull(varysProxyMpUrlProvider);
        assertEquals(new VarysProxyMpUrlProvider().url(Query.class),
                varysProxyMpUrlProvider.url(Query.class));

        val varysProxyCorpUrlProvider = injector
                .getInstance(VarysProxyCorpUrlProvider.class);
        assertNotNull(varysProxyCorpUrlProvider);
        assertEquals(new VarysProxyCorpUrlProvider().url(Query.class),
                varysProxyCorpUrlProvider.url(Query.class));

        val varysQueryUrlProvider = injector
                .getInstance(VarysQueryUrlProvider.class);
        assertNotNull(varysQueryUrlProvider);
        assertEquals(new VarysQueryUrlProvider().url(Query.class),
                varysQueryUrlProvider.url(Query.class));

        val varysCallTimeoutProvider = injector
                .getInstance(VarysCallTimeoutProvider.class);
        assertNotNull(varysCallTimeoutProvider);
        assertEquals(new VarysCallTimeoutProvider().timeout(Query.class),
                varysCallTimeoutProvider.timeout(Query.class));

        val varysConnectTimeoutProvider = injector
                .getInstance(VarysConnectTimeoutProvider.class);
        assertNotNull(varysConnectTimeoutProvider);
        assertEquals(new VarysConnectTimeoutProvider().timeout(Query.class),
                varysConnectTimeoutProvider.timeout(Query.class));

        val varysReadTimeoutProvider = injector
                .getInstance(VarysReadTimeoutProvider.class);
        assertNotNull(varysReadTimeoutProvider);
        assertEquals(new VarysReadTimeoutProvider().timeout(Query.class),
                varysReadTimeoutProvider.timeout(Query.class));

        val varysWriteTimeoutProvider = injector
                .getInstance(VarysWriteTimeoutProvider.class);
        assertNotNull(varysWriteTimeoutProvider);
        assertEquals(new VarysWriteTimeoutProvider().timeout(Query.class),
                varysWriteTimeoutProvider.timeout(Query.class));
    }
}

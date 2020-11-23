package com.github.charlemaznable.varystest.guice.defaultconfig;

import com.github.charlemaznable.varys.config.VarysConfig;
import com.github.charlemaznable.varys.guice.VarysModular;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varys.impl.VarysCallTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysConnectTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysProxyFengniaoAppUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatAppUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatCorpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatMpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatTpAuthUrlProvider;
import com.github.charlemaznable.varys.impl.VarysProxyWechatTpUrlProvider;
import com.github.charlemaznable.varys.impl.VarysQueryUrlProvider;
import com.github.charlemaznable.varys.impl.VarysReadTimeoutProvider;
import com.github.charlemaznable.varys.impl.VarysWriteTimeoutProvider;
import com.github.charlemaznable.varystest.proxy.ProxyFengniaoAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatMpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpAuthDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpDemo;
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

public class DefaultConfigTest {

    private static VarysModular varysModular;
    private static Injector injector;

    @BeforeAll
    public static void beforeAll() {
        varysModular = new VarysModular();
        injector = Guice.createInjector(varysModular.bindOtherClients(
                ProxyWechatAppDemo.class, ProxyWechatMpDemo.class,
                ProxyWechatTpDemo.class, ProxyWechatTpAuthDemo.class,
                ProxyWechatCorpDemo.class, ProxyFengniaoAppDemo.class).createModule());
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

            val wechatAppTokenResp = query.wechatAppToken("default");
            assertEquals("1000", wechatAppTokenResp.getAppId());
            assertEquals("defaultToken", wechatAppTokenResp.getToken());
            assertEquals("defaultTicket", wechatAppTokenResp.getTicket());

            val wechatMpLoginResp = query.wechatMpLogin("default", "JSCODE");
            assertEquals("openid", wechatMpLoginResp.getOpenId());
            assertEquals("session_key", wechatMpLoginResp.getSessionKey());
            assertEquals("unionid", wechatMpLoginResp.getUnionId());
            assertEquals(0, wechatMpLoginResp.getErrcode());
            assertEquals("OK", wechatMpLoginResp.getErrmsg());

            val wechatAppJsConfigResp = query.wechatAppJsConfig("default", "URL");
            assertEquals("1000", wechatAppJsConfigResp.getAppId());
            assertEquals("nonceStr", wechatAppJsConfigResp.getNonceStr());
            assertEquals(1000, wechatAppJsConfigResp.getTimestamp());
            assertEquals("signature", wechatAppJsConfigResp.getSignature());

            val wechatTpTokenResp = query.wechatTpToken("default");
            assertEquals("1000", wechatTpTokenResp.getAppId());
            assertEquals("defaultToken", wechatTpTokenResp.getToken());

            val wechatTpAuthTokenResp = query.wechatTpAuthToken("default", "abcd");
            assertEquals("1000", wechatTpAuthTokenResp.getAppId());
            assertEquals("abcd", wechatTpAuthTokenResp.getAuthorizerAppId());
            assertEquals("defaultToken", wechatTpAuthTokenResp.getToken());
            assertEquals("defaultTicket", wechatTpAuthTokenResp.getTicket());

            val wechatCorpTokenResp = query.wechatCorpToken("default");
            assertEquals("10000", wechatCorpTokenResp.getCorpId());
            assertEquals("defaultToken", wechatCorpTokenResp.getToken());

            val wechatCorpTpAuthTokenResp = query.wechatCorpTpAuthToken("default", "xyz");
            assertEquals("10000", wechatCorpTpAuthTokenResp.getCorpId());
            assertEquals("xyz", wechatCorpTpAuthTokenResp.getSuiteId());
            assertEquals("defaultToken", wechatCorpTpAuthTokenResp.getToken());

            val toutiaoAppTokenResp = query.toutiaoAppToken("default");
            assertEquals("2000", toutiaoAppTokenResp.getAppId());
            assertEquals("defaultToken", toutiaoAppTokenResp.getToken());

            val fengniaoAppTokenResp = query.fengniaoAppToken("default");
            assertEquals("3000", fengniaoAppTokenResp.getAppId());
            assertEquals("defaultToken", fengniaoAppTokenResp.getToken());
        });
    }

    @SneakyThrows
    @Test
    public void testDefaultConfigProxy() {
        proxyDefaultConfig(() -> {
            val proxyWechatApp = injector.getInstance(ProxyWechatAppDemo.class);
            val proxyWechatMp = injector.getInstance(ProxyWechatMpDemo.class);
            val proxyWechatTp = injector.getInstance(ProxyWechatTpDemo.class);
            val proxyWechatTpAuth = injector.getInstance(ProxyWechatTpAuthDemo.class);
            val proxyWechatCorp = injector.getInstance(ProxyWechatCorpDemo.class);
            val proxyFengniaoApp = injector.getInstance(ProxyFengniaoAppDemo.class);

            val wechatAppResp = proxyWechatApp.wechatApp("default", "b");
            assertEquals("defaultWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyWechatApp.wechatAppParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatAppParamResp", wechatAppParamResp);

            val wechatMpResp = proxyWechatMp.wechatMp("default", "b");
            assertEquals("defaultWechatMpResp", wechatMpResp);

            val wechatMpParamResp = proxyWechatMp.wechatMpParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatMpParamResp", wechatMpParamResp);

            val wechatTpResp = proxyWechatTp.wechatTp("default", "b");
            assertEquals("defaultWechatTpResp", wechatTpResp);

            val wechatTpParamResp = proxyWechatTp.wechatTpParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatTpParamResp", wechatTpParamResp);

            val wechatTpAuthResp = proxyWechatTpAuth.wechatTpAuth("default", "abcd", "b");
            assertEquals("defaultWechatTpAuthResp", wechatTpAuthResp);

            val wechatTpAuthParamResp = proxyWechatTpAuth.wechatTpAuthParam("default", "abcd", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatTpAuthParamResp", wechatTpAuthParamResp);

            val wechatCorpResp = proxyWechatCorp.wechatCorp("default", "b");
            assertEquals("defaultWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyWechatCorp.wechatCorpParam("default", "testParam", "b");
            assertEquals("defaultWechatCorpParamResp", wechatCorpParamResp);

            val fengniaoAppResp = proxyFengniaoApp.fengniaoApp("default", "b");
            assertEquals("defaultFengniaoAppResp", fengniaoAppResp);

            val fengniaoAppParamResp = proxyFengniaoApp.fengniaoAppParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultFengniaoAppParamResp", fengniaoAppParamResp);
        });
    }

    @Test
    public void testProxyError() {
        assertNull(injector.getInstance(VarysConfig.class));
        assertNotNull(injector.getInstance(VarysQueryUrlProvider.class));
    }

    @Test
    public void testContext() {
        assertNull(injector.getInstance(VarysConfig.class));

        val varysProxyAppUrlProvider = injector
                .getInstance(VarysProxyWechatAppUrlProvider.class);
        assertNotNull(varysProxyAppUrlProvider);
        assertEquals(new VarysProxyWechatAppUrlProvider().url(Query.class),
                varysProxyAppUrlProvider.url(Query.class));

        val varysProxyMpUrlProvider = injector
                .getInstance(VarysProxyWechatMpUrlProvider.class);
        assertNotNull(varysProxyMpUrlProvider);
        assertEquals(new VarysProxyWechatMpUrlProvider().url(Query.class),
                varysProxyMpUrlProvider.url(Query.class));

        val varysProxyTpUrlProvider = injector
                .getInstance(VarysProxyWechatTpUrlProvider.class);
        assertNotNull(varysProxyTpUrlProvider);
        assertEquals(new VarysProxyWechatTpUrlProvider().url(Query.class),
                varysProxyTpUrlProvider.url(Query.class));

        val varysProxyTpAuthUrlProvider = injector
                .getInstance(VarysProxyWechatTpAuthUrlProvider.class);
        assertNotNull(varysProxyTpAuthUrlProvider);
        assertEquals(new VarysProxyWechatTpAuthUrlProvider().url(Query.class),
                varysProxyTpAuthUrlProvider.url(Query.class));

        val varysProxyCorpUrlProvider = injector
                .getInstance(VarysProxyWechatCorpUrlProvider.class);
        assertNotNull(varysProxyCorpUrlProvider);
        assertEquals(new VarysProxyWechatCorpUrlProvider().url(Query.class),
                varysProxyCorpUrlProvider.url(Query.class));

        val varysProxyFengniaoAppUrlProvider = injector
                .getInstance(VarysProxyFengniaoAppUrlProvider.class);
        assertNotNull(varysProxyFengniaoAppUrlProvider);
        assertEquals(new VarysProxyFengniaoAppUrlProvider().url(Query.class),
                varysProxyFengniaoAppUrlProvider.url(Query.class));

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

package com.github.charlemaznable.varystest.guice.interfaceconfig;

import com.github.charlemaznable.varys.guice.VarysModular;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyFengniaoAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatMpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpAuthDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpDemo;
import com.github.charlemaznable.varystest.proxy.TestVarysScanAnchor;
import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.n3r.diamond.client.impl.MockDiamondServer;

import static com.github.charlemaznable.core.codec.Json.jsonOf;
import static com.github.charlemaznable.varystest.mock.InterfaceConfigMock.proxyInterfaceConfig;
import static com.github.charlemaznable.varystest.mock.InterfaceConfigMock.queryInterfaceConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterfaceConfigTest {

    private static VarysModular varysModular;
    private static Injector injector;

    @BeforeAll
    public static void beforeAll() {
        varysModular = new VarysModular(InterfaceConfig.class);
        injector = Guice.createInjector(varysModular.scanOtherClientPackageClasses(
                TestVarysScanAnchor.class).createModule());
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("Varys", "test",
                "InterfaceAddress=http://127.0.0.1:4236/varys\n");
    }

    @AfterAll
    public static void afterAll() {
        MockDiamondServer.tearDownMockServer();
    }

    @SneakyThrows
    @Test
    public void testInterfaceConfigQuery() {
        queryInterfaceConfig(() -> {
            val query = varysModular.getClient(Query.class);

            val wechatAppTokenResp = query.wechatAppToken("interface");
            assertEquals("1000", wechatAppTokenResp.getAppId());
            assertEquals("interfaceToken", wechatAppTokenResp.getToken());
            assertEquals("interfaceTicket", wechatAppTokenResp.getTicket());

            val wechatMpLoginResp = query.wechatMpLogin("interface", "JSCODE");
            assertEquals("openid", wechatMpLoginResp.getOpenId());
            assertEquals("session_key", wechatMpLoginResp.getSessionKey());
            assertEquals("unionid", wechatMpLoginResp.getUnionId());
            assertEquals(0, wechatMpLoginResp.getErrcode());
            assertEquals("OK", wechatMpLoginResp.getErrmsg());

            val wechatAppJsConfigResp = query.wechatAppJsConfig("interface", "URL");
            assertEquals("1000", wechatAppJsConfigResp.getAppId());
            assertEquals("nonceStr", wechatAppJsConfigResp.getNonceStr());
            assertEquals(1000, wechatAppJsConfigResp.getTimestamp());
            assertEquals("signature", wechatAppJsConfigResp.getSignature());

            val wechatTpTokenResp = query.wechatTpToken("interface");
            assertEquals("1000", wechatTpTokenResp.getAppId());
            assertEquals("interfaceToken", wechatTpTokenResp.getToken());

            val wechatTpAuthTokenResp = query.wechatTpAuthToken("interface", "abcd");
            assertEquals("1000", wechatTpAuthTokenResp.getAppId());
            assertEquals("abcd", wechatTpAuthTokenResp.getAuthorizerAppId());
            assertEquals("interfaceToken", wechatTpAuthTokenResp.getToken());
            assertEquals("interfaceTicket", wechatTpAuthTokenResp.getTicket());

            val wechatCorpTokenResp = query.wechatCorpToken("interface");
            assertEquals("10000", wechatCorpTokenResp.getCorpId());
            assertEquals("interfaceToken", wechatCorpTokenResp.getToken());

            val wechatCorpTpAuthTokenResp = query.wechatCorpTpAuthToken("interface", "xyz");
            assertEquals("10000", wechatCorpTpAuthTokenResp.getCorpId());
            assertEquals("xyz", wechatCorpTpAuthTokenResp.getSuiteId());
            assertEquals("interfaceToken", wechatCorpTpAuthTokenResp.getToken());

            val toutiaoAppTokenResp = query.toutiaoAppToken("interface");
            assertEquals("2000", toutiaoAppTokenResp.getAppId());
            assertEquals("interfaceToken", toutiaoAppTokenResp.getToken());

            val fengniaoAppTokenResp = query.fengniaoAppToken("interface");
            assertEquals("3000", fengniaoAppTokenResp.getAppId());
            assertEquals("interfaceToken", fengniaoAppTokenResp.getToken());
        });
    }

    @SneakyThrows
    @Test
    public void testInterfaceConfigProxy() {
        proxyInterfaceConfig(() -> {
            val proxyWechatApp = injector.getInstance(ProxyWechatAppDemo.class);
            val proxyWechatMp = injector.getInstance(ProxyWechatMpDemo.class);
            val proxyWechatTp = injector.getInstance(ProxyWechatTpDemo.class);
            val proxyWechatTpAuth = injector.getInstance(ProxyWechatTpAuthDemo.class);
            val proxyWechatCorp = injector.getInstance(ProxyWechatCorpDemo.class);
            val proxyFengniaoApp = injector.getInstance(ProxyFengniaoAppDemo.class);

            val wechatAppResp = proxyWechatApp.wechatApp("interface", "b");
            assertEquals("interfaceWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyWechatApp.wechatAppParam("interface", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceWechatAppParamResp", wechatAppParamResp);

            val wechatMpResp = proxyWechatMp.wechatMp("interface", "b");
            assertEquals("interfaceWechatMpResp", wechatMpResp);

            val wechatMpParamResp = proxyWechatMp.wechatMpParam("interface", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceWechatMpParamResp", wechatMpParamResp);

            val wechatTpResp = proxyWechatTp.wechatTp("interface", "b");
            assertEquals("interfaceWechatTpResp", wechatTpResp);

            val wechatTpParamResp = proxyWechatTp.wechatTpParam("interface", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceWechatTpParamResp", wechatTpParamResp);

            val wechatTpAuthResp = proxyWechatTpAuth.wechatTpAuth("interface", "abcd", "b");
            assertEquals("interfaceWechatTpAuthResp", wechatTpAuthResp);

            val wechatTpAuthParamResp = proxyWechatTpAuth.wechatTpAuthParam("interface", "abcd", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceWechatTpAuthParamResp", wechatTpAuthParamResp);

            val wechatCorpResp = proxyWechatCorp.wechatCorp("interface", "b");
            assertEquals("interfaceWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyWechatCorp.wechatCorpParam("interface", "testParam", "b");
            assertEquals("interfaceWechatCorpParamResp", wechatCorpParamResp);

            val fengniaoAppResp = proxyFengniaoApp.fengniaoApp("interface", "b");
            assertEquals("interfaceFengniaoAppResp", fengniaoAppResp);

            val fengniaoAppParamResp = proxyFengniaoApp.fengniaoAppParam("interface", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceFengniaoAppParamResp", fengniaoAppParamResp);
        });
    }
}

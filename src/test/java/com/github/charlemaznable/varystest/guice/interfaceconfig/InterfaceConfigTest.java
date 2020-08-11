package com.github.charlemaznable.varystest.guice.interfaceconfig;

import com.github.charlemaznable.varys.guice.VarysModular;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyMpDemo;
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

            val appTokenResp = query.appToken("interface");
            assertEquals("1000", appTokenResp.getAppId());
            assertEquals("interfaceToken", appTokenResp.getToken());

            val appAuthorizerTokenResp = query.appAuthorizerToken("interface", "abcd");
            assertEquals("1000", appAuthorizerTokenResp.getAppId());
            assertEquals("abcd", appAuthorizerTokenResp.getAuthorizerAppId());
            assertEquals("interfaceToken", appAuthorizerTokenResp.getToken());

            val corpTokenResp = query.corpToken("interface");
            assertEquals("10000", corpTokenResp.getCorpId());
            assertEquals("interfaceToken", corpTokenResp.getToken());

            val corpAuthorizerTokenResp = query.corpAuthorizerToken("interface", "xyz");
            assertEquals("10000", corpAuthorizerTokenResp.getCorpId());
            assertEquals("xyz", corpAuthorizerTokenResp.getSuiteId());
            assertEquals("interfaceToken", corpAuthorizerTokenResp.getToken());
        });
    }

    @SneakyThrows
    @Test
    public void testInterfaceConfigProxy() {
        proxyInterfaceConfig(() -> {
            val proxyApp = injector.getInstance(ProxyAppDemo.class);
            val proxyMp = injector.getInstance(ProxyMpDemo.class);
            val proxyCorp = injector.getInstance(ProxyCorpDemo.class);

            val wechatAppResp = proxyApp.wechatApp("interface", "b");
            assertEquals("interfaceWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyApp.wechatAppParam("interface", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceWechatAppParamResp", wechatAppParamResp);

            val wechatMpResp = proxyMp.wechatMp("interface", "b");
            assertEquals("interfaceWechatMpResp", wechatMpResp);

            val wechatMpParamResp = proxyMp.wechatMpParam("interface", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceWechatMpParamResp", wechatMpParamResp);

            val wechatCorpResp = proxyCorp.wechatCorp("interface", "b");
            assertEquals("interfaceWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyCorp.wechatCorpParam("interface", "testParam", "b");
            assertEquals("interfaceWechatCorpParamResp", wechatCorpParamResp);
        });
    }
}

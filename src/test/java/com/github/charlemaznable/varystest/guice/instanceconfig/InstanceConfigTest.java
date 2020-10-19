package com.github.charlemaznable.varystest.guice.instanceconfig;

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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;

import static com.github.charlemaznable.core.codec.Json.jsonOf;
import static com.github.charlemaznable.varystest.mock.InstanceConfigMock.proxyInstanceConfig;
import static com.github.charlemaznable.varystest.mock.InstanceConfigMock.queryInstanceConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstanceConfigTest {

    private static VarysModular varysModular;
    private static Injector injector;

    @BeforeAll
    public static void beforeAll() {
        varysModular = new VarysModular(new InstanceConfig());
        injector = Guice.createInjector(varysModular.scanOtherClientPackages(
                ClassUtils.getPackageName(TestVarysScanAnchor.class)).createModule());
    }

    @SneakyThrows
    @Test
    public void testInstanceConfigQuery() {
        queryInstanceConfig(() -> {
            val query = varysModular.getClient(Query.class);

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

            val toutiaoAppTokenResp = query.toutiaoAppToken("instance");
            assertEquals("2000", toutiaoAppTokenResp.getAppId());
            assertEquals("instanceToken", toutiaoAppTokenResp.getToken());

            val wechatMpLoginResp = query.wechatMpLogin("instance", "JSCODE");
            assertEquals("openid", wechatMpLoginResp.getOpenId());
            assertEquals("session_key", wechatMpLoginResp.getSessionKey());
            assertEquals("unionid", wechatMpLoginResp.getUnionId());
            assertEquals(0, wechatMpLoginResp.getErrcode());
            assertEquals("OK", wechatMpLoginResp.getErrmsg());
        });
    }

    @SneakyThrows
    @Test
    public void testInstanceConfigProxy() {
        proxyInstanceConfig(() -> {
            val proxyApp = injector.getInstance(ProxyAppDemo.class);
            val proxyMp = injector.getInstance(ProxyMpDemo.class);
            val proxyCorp = injector.getInstance(ProxyCorpDemo.class);

            val wechatAppResp = proxyApp.wechatApp("instance", "b");
            assertEquals("instanceWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyApp.wechatAppParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceWechatAppParamResp", wechatAppParamResp);

            val wechatMpResp = proxyMp.wechatMp("instance", "b");
            assertEquals("instanceWechatMpResp", wechatMpResp);

            val wechatMpParamResp = proxyMp.wechatMpParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceWechatMpParamResp", wechatMpParamResp);

            val wechatCorpResp = proxyCorp.wechatCorp("instance", "b");
            assertEquals("instanceWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyCorp.wechatCorpParam("instance", "testParam", "b");
            assertEquals("instanceWechatCorpParamResp", wechatCorpParamResp);
        });
    }
}

package com.github.charlemaznable.varystest.guice.instanceconfig;

import com.github.charlemaznable.varys.guice.VarysModular;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatMpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpDemo;
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

            val wechatAppTokenResp = query.wechatAppToken("instance");
            assertEquals("1000", wechatAppTokenResp.getAppId());
            assertEquals("instanceToken", wechatAppTokenResp.getToken());

            val wechatTpTokenResp = query.wechatTpToken("instance");
            assertEquals("1000", wechatTpTokenResp.getAppId());
            assertEquals("instanceToken", wechatTpTokenResp.getToken());

            val wechatTpAuthTokenResp = query.wechatTpAuthToken("instance", "abcd");
            assertEquals("1000", wechatTpAuthTokenResp.getAppId());
            assertEquals("abcd", wechatTpAuthTokenResp.getAuthorizerAppId());
            assertEquals("instanceToken", wechatTpAuthTokenResp.getToken());

            val wechatCorpTokenResp = query.wechatCorpToken("instance");
            assertEquals("10000", wechatCorpTokenResp.getCorpId());
            assertEquals("instanceToken", wechatCorpTokenResp.getToken());

            val wechatCorpTpAuthTokenResp = query.wechatCorpTpAuthToken("instance", "xyz");
            assertEquals("10000", wechatCorpTpAuthTokenResp.getCorpId());
            assertEquals("xyz", wechatCorpTpAuthTokenResp.getSuiteId());
            assertEquals("instanceToken", wechatCorpTpAuthTokenResp.getToken());

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
            val proxyWechatApp = injector.getInstance(ProxyWechatAppDemo.class);
            val proxyWechatMp = injector.getInstance(ProxyWechatMpDemo.class);
            val proxyWechatTp = injector.getInstance(ProxyWechatTpDemo.class);
            val proxyWechatCorp = injector.getInstance(ProxyWechatCorpDemo.class);

            val wechatAppResp = proxyWechatApp.wechatApp("instance", "b");
            assertEquals("instanceWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyWechatApp.wechatAppParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceWechatAppParamResp", wechatAppParamResp);

            val wechatMpResp = proxyWechatMp.wechatMp("instance", "b");
            assertEquals("instanceWechatMpResp", wechatMpResp);

            val wechatMpParamResp = proxyWechatMp.wechatMpParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceWechatMpParamResp", wechatMpParamResp);

            val wechatTpResp = proxyWechatTp.wechatTp("instance", "b");
            assertEquals("instanceWechatTpResp", wechatTpResp);

            val wechatTpParamResp = proxyWechatTp.wechatTpParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceWechatTpParamResp", wechatTpParamResp);

            val wechatCorpResp = proxyWechatCorp.wechatCorp("instance", "b");
            assertEquals("instanceWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyWechatCorp.wechatCorpParam("instance", "testParam", "b");
            assertEquals("instanceWechatCorpParamResp", wechatCorpParamResp);
        });
    }
}

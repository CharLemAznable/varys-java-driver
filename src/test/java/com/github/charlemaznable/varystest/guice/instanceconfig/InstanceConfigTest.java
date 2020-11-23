package com.github.charlemaznable.varystest.guice.instanceconfig;

import com.github.charlemaznable.varys.guice.VarysModular;
import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyFengniaoAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpAuthDemo;
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
            assertEquals("instanceTicket", wechatAppTokenResp.getTicket());

            val wechatAppMpLoginResp = query.wechatAppMpLogin("instance", "JSCODE");
            assertEquals("openid", wechatAppMpLoginResp.getOpenId());
            assertEquals("session_key", wechatAppMpLoginResp.getSessionKey());
            assertEquals("unionid", wechatAppMpLoginResp.getUnionId());
            assertEquals(0, wechatAppMpLoginResp.getErrcode());
            assertEquals("OK", wechatAppMpLoginResp.getErrmsg());

            val wechatAppJsConfigResp = query.wechatAppJsConfig("instance", "URL");
            assertEquals("1000", wechatAppJsConfigResp.getAppId());
            assertEquals("nonceStr", wechatAppJsConfigResp.getNonceStr());
            assertEquals(1000, wechatAppJsConfigResp.getTimestamp());
            assertEquals("signature", wechatAppJsConfigResp.getSignature());

            val wechatTpTokenResp = query.wechatTpToken("instance");
            assertEquals("1000", wechatTpTokenResp.getAppId());
            assertEquals("instanceToken", wechatTpTokenResp.getToken());

            val wechatTpAuthTokenResp = query.wechatTpAuthToken("instance", "abcd");
            assertEquals("1000", wechatTpAuthTokenResp.getAppId());
            assertEquals("abcd", wechatTpAuthTokenResp.getAuthorizerAppId());
            assertEquals("instanceToken", wechatTpAuthTokenResp.getToken());
            assertEquals("instanceTicket", wechatTpAuthTokenResp.getTicket());

            val wechatTpAuthMpLoginResp = query.wechatTpAuthMpLogin("instance", "abcd", "JSCODE");
            assertEquals("openid", wechatTpAuthMpLoginResp.getOpenId());
            assertEquals("session_key", wechatTpAuthMpLoginResp.getSessionKey());
            assertEquals(0, wechatTpAuthMpLoginResp.getErrcode());
            assertEquals("OK", wechatTpAuthMpLoginResp.getErrmsg());

            val wechatTpAuthJsConfigResp = query.wechatTpAuthJsConfig("instance", "abcd", "URL");
            assertEquals("abcd", wechatTpAuthJsConfigResp.getAppId());
            assertEquals("nonceStr", wechatTpAuthJsConfigResp.getNonceStr());
            assertEquals(1000, wechatTpAuthJsConfigResp.getTimestamp());
            assertEquals("signature", wechatTpAuthJsConfigResp.getSignature());

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

            val fengniaoAppTokenResp = query.fengniaoAppToken("instance");
            assertEquals("3000", fengniaoAppTokenResp.getAppId());
            assertEquals("instanceToken", fengniaoAppTokenResp.getToken());
        });
    }

    @SneakyThrows
    @Test
    public void testInstanceConfigProxy() {
        proxyInstanceConfig(() -> {
            val proxyWechatApp = injector.getInstance(ProxyWechatAppDemo.class);
            val proxyWechatTp = injector.getInstance(ProxyWechatTpDemo.class);
            val proxyWechatTpAuth = injector.getInstance(ProxyWechatTpAuthDemo.class);
            val proxyWechatCorp = injector.getInstance(ProxyWechatCorpDemo.class);
            val proxyFengniaoApp = injector.getInstance(ProxyFengniaoAppDemo.class);

            val wechatAppResp = proxyWechatApp.wechatApp("instance", "b");
            assertEquals("instanceWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyWechatApp.wechatAppParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceWechatAppParamResp", wechatAppParamResp);

            val wechatTpResp = proxyWechatTp.wechatTp("instance", "b");
            assertEquals("instanceWechatTpResp", wechatTpResp);

            val wechatTpParamResp = proxyWechatTp.wechatTpParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceWechatTpParamResp", wechatTpParamResp);

            val wechatTpAuthResp = proxyWechatTpAuth.wechatTpAuth("instance", "abcd", "b");
            assertEquals("instanceWechatTpAuthResp", wechatTpAuthResp);

            val wechatTpAuthParamResp = proxyWechatTpAuth.wechatTpAuthParam("instance", "abcd", "testParam", jsonOf("a", "b"));
            assertEquals("instanceWechatTpAuthParamResp", wechatTpAuthParamResp);

            val wechatCorpResp = proxyWechatCorp.wechatCorp("instance", "b");
            assertEquals("instanceWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyWechatCorp.wechatCorpParam("instance", "testParam", "b");
            assertEquals("instanceWechatCorpParamResp", wechatCorpParamResp);

            val fengniaoAppResp = proxyFengniaoApp.fengniaoApp("instance", "b");
            assertEquals("instanceFengniaoAppResp", fengniaoAppResp);

            val fengniaoAppParamResp = proxyFengniaoApp.fengniaoAppParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceFengniaoAppParamResp", fengniaoAppParamResp);
        });
    }
}

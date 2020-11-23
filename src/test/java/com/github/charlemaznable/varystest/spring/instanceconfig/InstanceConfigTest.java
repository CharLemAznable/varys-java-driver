package com.github.charlemaznable.varystest.spring.instanceconfig;

import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyFengniaoAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatMpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpAuthDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpDemo;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.charlemaznable.core.codec.Json.jsonOf;
import static com.github.charlemaznable.varystest.mock.InstanceConfigMock.proxyInstanceConfig;
import static com.github.charlemaznable.varystest.mock.InstanceConfigMock.queryInstanceConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = InstanceConfiguration.class)
public class InstanceConfigTest {

    @Autowired
    private Query query;
    @Autowired
    private ProxyWechatAppDemo proxyWechatApp;
    @Autowired
    private ProxyWechatMpDemo proxyWechatMp;
    @Autowired
    private ProxyWechatTpDemo proxyWechatTp;
    @Autowired
    private ProxyWechatTpAuthDemo proxyWechatTpAuth;
    @Autowired
    private ProxyWechatCorpDemo proxyWechatCorp;
    @Autowired
    private ProxyFengniaoAppDemo proxyFengniaoApp;

    @SneakyThrows
    @Test
    public void testInstanceConfigQuery() {
        queryInstanceConfig(() -> {
            val wechatAppTokenResp = query.wechatAppToken("instance");
            assertEquals("1000", wechatAppTokenResp.getAppId());
            assertEquals("instanceToken", wechatAppTokenResp.getToken());
            assertEquals("instanceTicket", wechatAppTokenResp.getTicket());

            val wechatMpLoginResp = query.wechatMpLogin("instance", "JSCODE");
            assertEquals("openid", wechatMpLoginResp.getOpenId());
            assertEquals("session_key", wechatMpLoginResp.getSessionKey());
            assertEquals("unionid", wechatMpLoginResp.getUnionId());
            assertEquals(0, wechatMpLoginResp.getErrcode());
            assertEquals("OK", wechatMpLoginResp.getErrmsg());

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

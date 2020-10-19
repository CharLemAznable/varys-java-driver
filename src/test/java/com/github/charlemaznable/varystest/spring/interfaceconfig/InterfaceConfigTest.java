package com.github.charlemaznable.varystest.spring.interfaceconfig;

import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varystest.proxy.ProxyAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyMpDemo;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.charlemaznable.core.codec.Json.jsonOf;
import static com.github.charlemaznable.varystest.mock.InterfaceConfigMock.proxyInterfaceConfig;
import static com.github.charlemaznable.varystest.mock.InterfaceConfigMock.queryInterfaceConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = InterfaceConfiguration.class)
public class InterfaceConfigTest {

    @Autowired
    private Query query;
    @Autowired
    private ProxyAppDemo proxyApp;
    @Autowired
    private ProxyMpDemo proxyMp;
    @Autowired
    private ProxyCorpDemo proxyCorp;

    @SneakyThrows
    @Test
    public void testInterfaceConfigQuery() {
        queryInterfaceConfig(() -> {
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

            val toutiaoAppTokenResp = query.toutiaoAppToken("interface");
            assertEquals("2000", toutiaoAppTokenResp.getAppId());
            assertEquals("interfaceToken", toutiaoAppTokenResp.getToken());

            val wechatMpLoginResp = query.wechatMpLogin("interface", "JSCODE");
            assertEquals("openid", wechatMpLoginResp.getOpenId());
            assertEquals("session_key", wechatMpLoginResp.getSessionKey());
            assertEquals("unionid", wechatMpLoginResp.getUnionId());
            assertEquals(0, wechatMpLoginResp.getErrcode());
            assertEquals("OK", wechatMpLoginResp.getErrmsg());
        });
    }

    @SneakyThrows
    @Test
    public void testInterfaceConfigProxy() {
        proxyInterfaceConfig(() -> {
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

package com.github.charlemaznable.varystest.spring.instanceconfig;

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
    private ProxyAppDemo proxyApp;
    @Autowired
    private ProxyMpDemo proxyMp;
    @Autowired
    private ProxyCorpDemo proxyCorp;

    @SneakyThrows
    @Test
    public void testInstanceConfigQuery() {
        queryInstanceConfig(() -> {
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
        });
    }

    @SneakyThrows
    @Test
    public void testInstanceConfigProxy() {
        proxyInstanceConfig(() -> {
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

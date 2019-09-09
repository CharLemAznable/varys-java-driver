package com.github.charlemaznable.varys.instanceConfig;

import com.github.charlemaznable.varys.spring.Varys;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = InstanceConfiguration.class)
public class InstanceConfigTest {

    @Autowired
    private Varys varys;

    @Test
    public void testInstanceConfigQuery() {
        val appTokenResp = varys.query().appToken("instance");
        assertEquals("1000", appTokenResp.getAppId());
        assertEquals("instanceToken", appTokenResp.getToken());

        val appAuthorizerTokenResp = varys.query().appAuthorizerToken("instance", "abcd");
        assertEquals("1000", appAuthorizerTokenResp.getAppId());
        assertEquals("abcd", appAuthorizerTokenResp.getAuthorizerAppId());
        assertEquals("instanceToken", appAuthorizerTokenResp.getToken());

        val corpTokenResp = varys.query().corpToken("instance");
        assertEquals("10000", corpTokenResp.getCorpId());
        assertEquals("instanceToken", corpTokenResp.getToken());

        val corpAuthorizerTokenResp = varys.query().corpAuthorizerToken("instance", "xyz");
        assertEquals("10000", corpAuthorizerTokenResp.getCorpId());
        assertEquals("xyz", corpAuthorizerTokenResp.getSuiteId());
        assertEquals("instanceToken", corpAuthorizerTokenResp.getToken());
    }

    @Test
    public void testInstanceConfigProxy() {
        val wechatAppResp = varys.proxy().wechatApp("instance", "/wechatApp").get();
        assertEquals("instanceWechatAppResp", wechatAppResp);

        val wechatAppParamResp = varys.proxy().wechatApp("instance", "wechatAppParam/%s", "testParam").post();
        assertEquals("instanceWechatAppParamResp", wechatAppParamResp);

        val wechatCorpResp = varys.proxy().wechatCorp("instance", "/wechatCorp").get();
        assertEquals("instanceWechatCorpResp", wechatCorpResp);

        val wechatCorpParamResp = varys.proxy().wechatCorp("instance", "wechatCorpParam/%s", "testParam").post();
        assertEquals("instanceWechatCorpParamResp", wechatCorpParamResp);
    }
}

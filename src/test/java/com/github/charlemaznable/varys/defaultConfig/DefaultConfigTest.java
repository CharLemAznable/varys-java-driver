package com.github.charlemaznable.varys.defaultConfig;

import com.github.charlemaznable.varys.spring.Varys;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = DefaultConfiguration.class)
public class DefaultConfigTest {

    @Autowired
    private Varys varys;

    @Test
    public void testDefaultConfigQuery() {
        val appTokenResp = varys.query().appToken("default");
        assertEquals("1000", appTokenResp.getAppId());
        assertEquals("defaultToken", appTokenResp.getToken());

        val appAuthorizerTokenResp = varys.query().appAuthorizerToken("default", "abcd");
        assertEquals("1000", appAuthorizerTokenResp.getAppId());
        assertEquals("abcd", appAuthorizerTokenResp.getAuthorizerAppId());
        assertEquals("defaultToken", appAuthorizerTokenResp.getToken());

        val corpTokenResp = varys.query().corpToken("default");
        assertEquals("10000", corpTokenResp.getCorpId());
        assertEquals("defaultToken", corpTokenResp.getToken());

        val corpAuthorizerTokenResp = varys.query().corpAuthorizerToken("default", "xyz");
        assertEquals("10000", corpAuthorizerTokenResp.getCorpId());
        assertEquals("xyz", corpAuthorizerTokenResp.getSuiteId());
        assertEquals("defaultToken", corpAuthorizerTokenResp.getToken());
    }

    @Test
    public void testDefaultConfigProxy() {
        val wechatAppResp = varys.proxy().wechatApp("default", "/wechatApp").get();
        assertEquals("defaultWechatAppResp", wechatAppResp);

        val wechatAppParamResp = varys.proxy().wechatApp("default", "wechatAppParam/%s", "testParam").post();
        assertEquals("defaultWechatAppParamResp", wechatAppParamResp);

        val wechatCorpResp = varys.proxy().wechatCorp("default", "/wechatCorp").get();
        assertEquals("defaultWechatCorpResp", wechatCorpResp);

        val wechatCorpParamResp = varys.proxy().wechatCorp("default", "wechatCorpParam/%s", "testParam").post();
        assertEquals("defaultWechatCorpParamResp", wechatCorpParamResp);
    }
}

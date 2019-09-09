package com.github.charlemaznable.varys.interfaceConfig;

import com.github.charlemaznable.varys.spring.Varys;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = InterfaceConfiguration.class)
public class InterfaceConfigTest {

    @Autowired
    private Varys varys;

    @Test
    public void testInterfaceConfigQuery() {
        val appTokenResp = varys.query().appToken("interface");
        assertEquals("1000", appTokenResp.getAppId());
        assertEquals("interfaceToken", appTokenResp.getToken());

        val appAuthorizerTokenResp = varys.query().appAuthorizerToken("interface", "abcd");
        assertEquals("1000", appAuthorizerTokenResp.getAppId());
        assertEquals("abcd", appAuthorizerTokenResp.getAuthorizerAppId());
        assertEquals("interfaceToken", appAuthorizerTokenResp.getToken());

        val corpTokenResp = varys.query().corpToken("interface");
        assertEquals("10000", corpTokenResp.getCorpId());
        assertEquals("interfaceToken", corpTokenResp.getToken());

        val corpAuthorizerTokenResp = varys.query().corpAuthorizerToken("interface", "xyz");
        assertEquals("10000", corpAuthorizerTokenResp.getCorpId());
        assertEquals("xyz", corpAuthorizerTokenResp.getSuiteId());
        assertEquals("interfaceToken", corpAuthorizerTokenResp.getToken());
    }

    @Test
    public void testInterfaceConfigProxy() {
        val wechatAppResp = varys.proxy().wechatApp("interface", "/wechatApp").get();
        assertEquals("interfaceWechatAppResp", wechatAppResp);

        val wechatAppParamResp = varys.proxy().wechatApp("interface", "wechatAppParam/%s", "testParam").post();
        assertEquals("interfaceWechatAppParamResp", wechatAppParamResp);

        val wechatCorpResp = varys.proxy().wechatCorp("interface", "/wechatCorp").get();
        assertEquals("interfaceWechatCorpResp", wechatCorpResp);

        val wechatCorpParamResp = varys.proxy().wechatCorp("interface", "wechatCorpParam/%s", "testParam").post();
        assertEquals("interfaceWechatCorpParamResp", wechatCorpParamResp);
    }
}

package com.github.charlemaznable.varys.interfaceConfig;

import com.github.charlemaznable.core.net.HttpReq;
import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;
import com.github.charlemaznable.varys.spring.Varys;
import lombok.val;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.charlemaznable.core.codec.Json.json;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = InterfaceConfiguration.class)
public class InterfaceConfigTest {

    @Autowired
    private Varys varys;

    @Test
    public void testInterfaceConfigQuery() {
        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                val resp = new AppTokenResp();
                resp.setAppId("1000");
                resp.setToken("interfaceToken");
                return json(resp);
            }
        };
        val appTokenResp = varys.query().appToken("interface");
        assertEquals("1000", appTokenResp.getAppId());
        assertEquals("interfaceToken", appTokenResp.getToken());

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                val resp = new AppAuthorizerTokenResp();
                resp.setAppId("1000");
                resp.setAuthorizerAppId("abcd");
                resp.setToken("interfaceToken");
                return json(resp);
            }
        };
        val appAuthorizerTokenResp = varys.query().appAuthorizerToken("interface", "abcd");
        assertEquals("1000", appAuthorizerTokenResp.getAppId());
        assertEquals("abcd", appAuthorizerTokenResp.getAuthorizerAppId());
        assertEquals("interfaceToken", appAuthorizerTokenResp.getToken());

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                val resp = new CorpTokenResp();
                resp.setCorpId("10000");
                resp.setToken("interfaceToken");
                return json(resp);
            }
        };
        val corpTokenResp = varys.query().corpToken("interface");
        assertEquals("10000", corpTokenResp.getCorpId());
        assertEquals("interfaceToken", corpTokenResp.getToken());

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                val resp = new CorpAuthorizerTokenResp();
                resp.setCorpId("10000");
                resp.setSuiteId("xyz");
                resp.setToken("interfaceToken");
                return json(resp);
            }
        };
        val corpAuthorizerTokenResp = varys.query().corpAuthorizerToken("interface", "xyz");
        assertEquals("10000", corpAuthorizerTokenResp.getCorpId());
        assertEquals("xyz", corpAuthorizerTokenResp.getSuiteId());
        assertEquals("interfaceToken", corpAuthorizerTokenResp.getToken());
    }

    @Test
    public void testInterfaceConfigProxy() {
        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                return "interfaceWechatAppResp";
            }
        };
        val wechatAppResp = varys.proxy().wechatApp("interface", "/wechatApp").get();
        assertEquals("interfaceWechatAppResp", wechatAppResp);

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String post() {
                return "interfaceWechatAppParamResp";
            }
        };
        val wechatAppParamResp = varys.proxy().wechatApp("interface", "wechatAppParam/%s", "testParam").post();
        assertEquals("interfaceWechatAppParamResp", wechatAppParamResp);

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                return "interfaceWechatCorpResp";
            }
        };
        val wechatCorpResp = varys.proxy().wechatCorp("interface", "/wechatCorp").get();
        assertEquals("interfaceWechatCorpResp", wechatCorpResp);

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String post() {
                return "interfaceWechatCorpParamResp";
            }
        };
        val wechatCorpParamResp = varys.proxy().wechatCorp("interface", "wechatCorpParam/%s", "testParam").post();
        assertEquals("interfaceWechatCorpParamResp", wechatCorpParamResp);
    }
}

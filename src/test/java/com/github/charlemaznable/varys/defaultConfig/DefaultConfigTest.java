package com.github.charlemaznable.varys.defaultConfig;

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

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = DefaultConfiguration.class)
public class DefaultConfigTest {

    @Autowired
    private Varys varys;

    @Test
    public void testDefaultConfigQuery() {
        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                val resp = new AppTokenResp();
                resp.setAppId("1000");
                resp.setToken("defaultToken");
                return json(resp);
            }
        };
        val appTokenResp = varys.query().appToken("default");
        assertEquals("1000", appTokenResp.getAppId());
        assertEquals("defaultToken", appTokenResp.getToken());

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                val resp = new AppAuthorizerTokenResp();
                resp.setAppId("1000");
                resp.setAuthorizerAppId("abcd");
                resp.setToken("defaultToken");
                return json(resp);
            }
        };
        val appAuthorizerTokenResp = varys.query().appAuthorizerToken("default", "abcd");
        assertEquals("1000", appAuthorizerTokenResp.getAppId());
        assertEquals("abcd", appAuthorizerTokenResp.getAuthorizerAppId());
        assertEquals("defaultToken", appAuthorizerTokenResp.getToken());

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                val resp = new CorpTokenResp();
                resp.setCorpId("10000");
                resp.setToken("defaultToken");
                return json(resp);
            }
        };
        val corpTokenResp = varys.query().corpToken("default");
        assertEquals("10000", corpTokenResp.getCorpId());
        assertEquals("defaultToken", corpTokenResp.getToken());

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                val resp = new CorpAuthorizerTokenResp();
                resp.setCorpId("10000");
                resp.setSuiteId("xyz");
                resp.setToken("defaultToken");
                return json(resp);
            }
        };
        val corpAuthorizerTokenResp = varys.query().corpAuthorizerToken("default", "xyz");
        assertEquals("10000", corpAuthorizerTokenResp.getCorpId());
        assertEquals("xyz", corpAuthorizerTokenResp.getSuiteId());
        assertEquals("defaultToken", corpAuthorizerTokenResp.getToken());
    }

    @Test
    public void testDefaultConfigProxy() {
        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                return "defaultWechatAppResp";
            }
        };
        val wechatAppResp = varys.proxy().wechatApp("default", "/wechatApp").get();
        assertEquals("defaultWechatAppResp", wechatAppResp);

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String post() {
                return "defaultWechatAppParamResp";
            }
        };
        val wechatAppParamResp = varys.proxy().wechatApp("default", "wechatAppParam/%s", "testParam").post();
        assertEquals("defaultWechatAppParamResp", wechatAppParamResp);

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                return "defaultWechatCorpResp";
            }
        };
        val wechatCorpResp = varys.proxy().wechatCorp("default", "/wechatCorp").get();
        assertEquals("defaultWechatCorpResp", wechatCorpResp);

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String post() {
                return "defaultWechatCorpParamResp";
            }
        };
        val wechatCorpParamResp = varys.proxy().wechatCorp("default", "wechatCorpParam/%s", "testParam").post();
        assertEquals("defaultWechatCorpParamResp", wechatCorpParamResp);
    }
}

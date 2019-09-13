package com.github.charlemaznable.varys.instanceConfig;

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
@ContextConfiguration(classes = InstanceConfiguration.class)
public class InstanceConfigTest {

    @Autowired
    private Varys varys;

    @Test
    public void testInstanceConfigQuery() {
        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                val resp = new AppTokenResp();
                resp.setAppId("1000");
                resp.setToken("instanceToken");
                return json(resp);
            }
        };
        val appTokenResp = varys.query().appToken("instance");
        assertEquals("1000", appTokenResp.getAppId());
        assertEquals("instanceToken", appTokenResp.getToken());

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                val resp = new AppAuthorizerTokenResp();
                resp.setAppId("1000");
                resp.setAuthorizerAppId("abcd");
                resp.setToken("instanceToken");
                return json(resp);
            }
        };
        val appAuthorizerTokenResp = varys.query().appAuthorizerToken("instance", "abcd");
        assertEquals("1000", appAuthorizerTokenResp.getAppId());
        assertEquals("abcd", appAuthorizerTokenResp.getAuthorizerAppId());
        assertEquals("instanceToken", appAuthorizerTokenResp.getToken());

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                val resp = new CorpTokenResp();
                resp.setCorpId("10000");
                resp.setToken("instanceToken");
                return json(resp);
            }
        };
        val corpTokenResp = varys.query().corpToken("instance");
        assertEquals("10000", corpTokenResp.getCorpId());
        assertEquals("instanceToken", corpTokenResp.getToken());

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                val resp = new CorpAuthorizerTokenResp();
                resp.setCorpId("10000");
                resp.setSuiteId("xyz");
                resp.setToken("instanceToken");
                return json(resp);
            }
        };
        val corpAuthorizerTokenResp = varys.query().corpAuthorizerToken("instance", "xyz");
        assertEquals("10000", corpAuthorizerTokenResp.getCorpId());
        assertEquals("xyz", corpAuthorizerTokenResp.getSuiteId());
        assertEquals("instanceToken", corpAuthorizerTokenResp.getToken());
    }

    @Test
    public void testInstanceConfigProxy() {
        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                return "instanceWechatAppResp";
            }
        };
        val wechatAppResp = varys.proxy().wechatApp("instance", "/wechatApp").get();
        assertEquals("instanceWechatAppResp", wechatAppResp);

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String post() {
                return "instanceWechatAppParamResp";
            }
        };
        val wechatAppParamResp = varys.proxy().wechatApp("instance", "wechatAppParam/%s", "testParam").post();
        assertEquals("instanceWechatAppParamResp", wechatAppParamResp);

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String get() {
                return "instanceWechatCorpResp";
            }
        };
        val wechatCorpResp = varys.proxy().wechatCorp("instance", "/wechatCorp").get();
        assertEquals("instanceWechatCorpResp", wechatCorpResp);

        new MockUp<HttpReq>(HttpReq.class) {
            @Mock
            public String post() {
                return "instanceWechatCorpParamResp";
            }
        };
        val wechatCorpParamResp = varys.proxy().wechatCorp("instance", "wechatCorpParam/%s", "testParam").post();
        assertEquals("instanceWechatCorpParamResp", wechatCorpParamResp);
    }
}

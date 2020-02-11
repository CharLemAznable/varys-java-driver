package com.github.charlemaznable.varystest.spring.instanceConfig;

import com.github.charlemaznable.varys.spring.Query;
import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;
import com.github.charlemaznable.varystest.spring.proxy.ProxyAppDemo;
import com.github.charlemaznable.varystest.spring.proxy.ProxyCorpDemo;
import lombok.SneakyThrows;
import lombok.val;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.github.charlemaznable.core.codec.Json.json;
import static com.github.charlemaznable.core.codec.Json.jsonOf;
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
    private ProxyCorpDemo proxyCorp;

    @SneakyThrows
    @Test
    public void testInstanceConfigQuery() {
        val mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) {
                switch (request.getPath()) {
                    case "/varys/query-wechat-app-token/instance":
                        val resp1 = new AppTokenResp();
                        resp1.setAppId("1000");
                        resp1.setToken("instanceToken");
                        return new MockResponse().setBody(json(resp1));

                    case "/varys/query-wechat-app-authorizer-token/instance/abcd":
                        val resp2 = new AppAuthorizerTokenResp();
                        resp2.setAppId("1000");
                        resp2.setAuthorizerAppId("abcd");
                        resp2.setToken("instanceToken");
                        return new MockResponse().setBody(json(resp2));

                    case "/varys/query-wechat-corp-token/instance":
                        val resp3 = new CorpTokenResp();
                        resp3.setCorpId("10000");
                        resp3.setToken("instanceToken");
                        return new MockResponse().setBody(json(resp3));

                    case "/varys/query-wechat-corp-authorizer-token/instance/xyz":
                        val resp4 = new CorpAuthorizerTokenResp();
                        resp4.setCorpId("10000");
                        resp4.setSuiteId("xyz");
                        resp4.setToken("instanceToken");
                        return new MockResponse().setBody(json(resp4));
                }
                return new MockResponse()
                        .setResponseCode(HttpStatus.NOT_FOUND.value())
                        .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
            }
        });
        mockWebServer.start(4236);

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

        mockWebServer.shutdown();
    }

    @SneakyThrows
    @Test
    public void testInstanceConfigProxy() {
        val mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) {
                switch (request.getPath()) {
                    case "/varys/proxy-wechat-app/instance/wechatApp":
                        assertEquals("b", request.getHeader("a"));
                        return new MockResponse().setBody("instanceWechatAppResp");

                    case "/varys/proxy-wechat-app/instance/wechatAppParam/testParam":
                        assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                        return new MockResponse().setBody("instanceWechatAppParamResp");

                    case "/varys/proxy-wechat-corp/instance/wechatCorp?a=b":
                        return new MockResponse().setBody("instanceWechatCorpResp");

                    case "/varys/proxy-wechat-corp/instance/wechatCorpParam/testParam":
                        assertEquals("a=b", request.getBody().readUtf8());
                        return new MockResponse().setBody("instanceWechatCorpParamResp");
                }
                return new MockResponse()
                        .setResponseCode(HttpStatus.NOT_FOUND.value())
                        .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
            }
        });
        mockWebServer.start(4236);

        val wechatAppResp = proxyApp.wechatApp("instance", "b");
        assertEquals("instanceWechatAppResp", wechatAppResp);

        val wechatAppParamResp = proxyApp.wechatAppParam("instance", "testParam", jsonOf("a", "b"));
        assertEquals("instanceWechatAppParamResp", wechatAppParamResp);

        val wechatCorpResp = proxyCorp.wechatCorp("instance", "b");
        assertEquals("instanceWechatCorpResp", wechatCorpResp);

        val wechatCorpParamResp = proxyCorp.wechatCorpParam("instance", "testParam", "b");
        assertEquals("instanceWechatCorpParamResp", wechatCorpParamResp);

        mockWebServer.shutdown();
    }
}

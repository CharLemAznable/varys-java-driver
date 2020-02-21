package com.github.charlemaznable.varystest.spring.defaultConfig;

import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;
import com.github.charlemaznable.varys.spring.Query;
import com.github.charlemaznable.varystest.spring.proxy.ProxyAppDemo;
import com.github.charlemaznable.varystest.spring.proxy.ProxyCorpDemo;
import com.github.charlemaznable.varystest.spring.proxy.ProxyError;
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
import static com.github.charlemaznable.core.net.ohclient.OhFactory.getClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DefaultConfiguration.class)
public class DefaultConfigTest {

    @Autowired
    private Query query;
    @Autowired
    private ProxyAppDemo proxyApp;
    @Autowired
    private ProxyCorpDemo proxyCorp;

    @SneakyThrows
    @Test
    public void testDefaultConfigQuery() {
        try (val mockWebServer = new MockWebServer()) {
            mockWebServer.setDispatcher(new Dispatcher() {
                @Override
                public MockResponse dispatch(RecordedRequest request) {
                    switch (request.getPath()) {
                        case "/varys/query-wechat-app-token/default":
                            val resp1 = new AppTokenResp();
                            resp1.setAppId("1000");
                            resp1.setToken("defaultToken");
                            return new MockResponse().setBody(json(resp1));

                        case "/varys/query-wechat-app-authorizer-token/default/abcd":
                            val resp2 = new AppAuthorizerTokenResp();
                            resp2.setAppId("1000");
                            resp2.setAuthorizerAppId("abcd");
                            resp2.setToken("defaultToken");
                            return new MockResponse().setBody(json(resp2));

                        case "/varys/query-wechat-corp-token/default":
                            val resp3 = new CorpTokenResp();
                            resp3.setCorpId("10000");
                            resp3.setToken("defaultToken");
                            return new MockResponse().setBody(json(resp3));

                        case "/varys/query-wechat-corp-authorizer-token/default/xyz":
                            val resp4 = new CorpAuthorizerTokenResp();
                            resp4.setCorpId("10000");
                            resp4.setSuiteId("xyz");
                            resp4.setToken("defaultToken");
                            return new MockResponse().setBody(json(resp4));
                    }
                    return new MockResponse()
                            .setResponseCode(HttpStatus.NOT_FOUND.value())
                            .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
                }
            });
            mockWebServer.start(4236);

            val appTokenResp = query.appToken("default");
            assertEquals("1000", appTokenResp.getAppId());
            assertEquals("defaultToken", appTokenResp.getToken());

            val appAuthorizerTokenResp = query.appAuthorizerToken("default", "abcd");
            assertEquals("1000", appAuthorizerTokenResp.getAppId());
            assertEquals("abcd", appAuthorizerTokenResp.getAuthorizerAppId());
            assertEquals("defaultToken", appAuthorizerTokenResp.getToken());

            val corpTokenResp = query.corpToken("default");
            assertEquals("10000", corpTokenResp.getCorpId());
            assertEquals("defaultToken", corpTokenResp.getToken());

            val corpAuthorizerTokenResp = query.corpAuthorizerToken("default", "xyz");
            assertEquals("10000", corpAuthorizerTokenResp.getCorpId());
            assertEquals("xyz", corpAuthorizerTokenResp.getSuiteId());
            assertEquals("defaultToken", corpAuthorizerTokenResp.getToken());
        }
    }

    @SneakyThrows
    @Test
    public void testDefaultConfigProxy() {
        try (val mockWebServer = new MockWebServer()) {
            mockWebServer.setDispatcher(new Dispatcher() {
                @Override
                public MockResponse dispatch(RecordedRequest request) {
                    switch (request.getPath()) {
                        case "/varys/proxy-wechat-app/default/wechatApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("defaultWechatAppResp");

                        case "/varys/proxy-wechat-app/default/wechatAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("defaultWechatAppParamResp");

                        case "/varys/proxy-wechat-corp/default/wechatCorp?a=b":
                            return new MockResponse().setBody("defaultWechatCorpResp");

                        case "/varys/proxy-wechat-corp/default/wechatCorpParam/testParam":
                            assertEquals("a=b", request.getBody().readUtf8());
                            return new MockResponse().setBody("defaultWechatCorpParamResp");
                    }
                    return new MockResponse()
                            .setResponseCode(HttpStatus.NOT_FOUND.value())
                            .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
                }
            });
            mockWebServer.start(4236);

            val wechatAppResp = proxyApp.wechatApp("default", "b");
            assertEquals("defaultWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyApp.wechatAppParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatAppParamResp", wechatAppParamResp);

            val wechatCorpResp = proxyCorp.wechatCorp("default", "b");
            assertEquals("defaultWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyCorp.wechatCorpParam("default", "testParam", "b");
            assertEquals("defaultWechatCorpParamResp", wechatCorpParamResp);
        }
    }

    @Test
    public void testProxyError() {
        assertThrows(IllegalArgumentException.class, () ->
                getClient(ProxyError.class).proxyError("test"));
    }
}

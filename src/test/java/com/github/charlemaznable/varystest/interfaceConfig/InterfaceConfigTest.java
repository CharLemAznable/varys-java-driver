package com.github.charlemaznable.varystest.interfaceConfig;

import com.github.charlemaznable.varys.Query;
import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;
import com.github.charlemaznable.varystest.proxy.ProxyAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyCorpDemo;
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
@ContextConfiguration(classes = InterfaceConfiguration.class)
public class InterfaceConfigTest {

    @Autowired
    private Query query;
    @Autowired
    private ProxyAppDemo proxyApp;
    @Autowired
    private ProxyCorpDemo proxyCorp;

    @SneakyThrows
    @Test
    public void testInterfaceConfigQuery() {
        val mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) {
                switch (request.getPath()) {
                    case "/varys/query-wechat-app-token/interface":
                        val resp1 = new AppTokenResp();
                        resp1.setAppId("1000");
                        resp1.setToken("interfaceToken");
                        return new MockResponse().setBody(json(resp1));

                    case "/varys/query-wechat-app-authorizer-token/interface/abcd":
                        val resp2 = new AppAuthorizerTokenResp();
                        resp2.setAppId("1000");
                        resp2.setAuthorizerAppId("abcd");
                        resp2.setToken("interfaceToken");
                        return new MockResponse().setBody(json(resp2));

                    case "/varys/query-wechat-corp-token/interface":
                        val resp3 = new CorpTokenResp();
                        resp3.setCorpId("10000");
                        resp3.setToken("interfaceToken");
                        return new MockResponse().setBody(json(resp3));

                    case "/varys/query-wechat-corp-authorizer-token/interface/xyz":
                        val resp4 = new CorpAuthorizerTokenResp();
                        resp4.setCorpId("10000");
                        resp4.setSuiteId("xyz");
                        resp4.setToken("interfaceToken");
                        return new MockResponse().setBody(json(resp4));
                }
                return new MockResponse()
                        .setResponseCode(HttpStatus.NOT_FOUND.value())
                        .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
            }
        });
        mockWebServer.start(4236);

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

        mockWebServer.shutdown();
    }

    @SneakyThrows
    @Test
    public void testInterfaceConfigProxy() {
        val mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) {
                switch (request.getPath()) {
                    case "/varys/proxy-wechat-app/interface/wechatApp":
                        assertEquals("b", request.getHeader("a"));
                        return new MockResponse().setBody("interfaceWechatAppResp");

                    case "/varys/proxy-wechat-app/interface/wechatAppParam/testParam":
                        assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                        return new MockResponse().setBody("interfaceWechatAppParamResp");

                    case "/varys/proxy-wechat-corp/interface/wechatCorp?a=b":
                        return new MockResponse().setBody("interfaceWechatCorpResp");

                    case "/varys/proxy-wechat-corp/interface/wechatCorpParam/testParam":
                        assertEquals("a=b", request.getBody().readUtf8());
                        return new MockResponse().setBody("interfaceWechatCorpParamResp");
                }
                return new MockResponse()
                        .setResponseCode(HttpStatus.NOT_FOUND.value())
                        .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
            }
        });
        mockWebServer.start(4236);

        val wechatAppResp = proxyApp.wechatApp("interface", "b");
        assertEquals("interfaceWechatAppResp", wechatAppResp);

        val wechatAppParamResp = proxyApp.wechatAppParam("interface", "testParam", jsonOf("a", "b"));
        assertEquals("interfaceWechatAppParamResp", wechatAppParamResp);

        val wechatCorpResp = proxyCorp.wechatCorp("interface", "b");
        assertEquals("interfaceWechatCorpResp", wechatCorpResp);

        val wechatCorpParamResp = proxyCorp.wechatCorpParam("interface", "testParam", "b");
        assertEquals("interfaceWechatCorpParamResp", wechatCorpParamResp);

        mockWebServer.shutdown();
    }
}

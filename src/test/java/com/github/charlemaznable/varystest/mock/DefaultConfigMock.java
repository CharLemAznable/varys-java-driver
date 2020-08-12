package com.github.charlemaznable.varystest.mock;

import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;
import lombok.SneakyThrows;
import lombok.val;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.function.Executable;
import org.springframework.http.HttpStatus;

import static com.github.charlemaznable.core.codec.Json.json;
import static com.github.charlemaznable.core.codec.Json.jsonOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultConfigMock {

    @SneakyThrows
    public static void queryDefaultConfig(Executable executable) {
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

                        case "/varys/query-toutiao-app-token/default":
                            val resp5 = new AppTokenResp();
                            resp5.setAppId("2000");
                            resp5.setToken("defaultToken");
                            return new MockResponse().setBody(json(resp5));
                    }
                    return new MockResponse()
                            .setResponseCode(HttpStatus.NOT_FOUND.value())
                            .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
                }
            });
            mockWebServer.start(4236);

            executable.execute();
        }
    }

    @SneakyThrows
    public static void proxyDefaultConfig(Executable executable) {
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

                        case "/varys/proxy-wechat-mp/default/wechatMp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("defaultWechatMpResp");

                        case "/varys/proxy-wechat-mp/default/wechatMpParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("defaultWechatMpParamResp");

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

            executable.execute();
        }
    }
}

package com.github.charlemaznable.varystest.mock;

import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;
import com.github.charlemaznable.varys.resp.WechatMpLoginResp;
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

public class InstanceConfigMock {

    @SneakyThrows
    public static void queryInstanceConfig(Executable executable) {
        try (val mockWebServer = new MockWebServer()) {
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

                        case "/varys/query-toutiao-app-token/instance":
                            val resp5 = new AppTokenResp();
                            resp5.setAppId("2000");
                            resp5.setToken("instanceToken");
                            return new MockResponse().setBody(json(resp5));

                        case "/varys/proxy-wechat-mp-login/instance?js_code=JSCODE":
                            val resp6 = new WechatMpLoginResp();
                            resp6.setOpenId("openid");
                            resp6.setSessionKey("session_key");
                            resp6.setUnionId("unionid");
                            resp6.setErrcode(0);
                            resp6.setErrmsg("OK");
                            return new MockResponse().setBody(json(resp6));
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
    public static void proxyInstanceConfig(Executable executable) {
        try (val mockWebServer = new MockWebServer()) {
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

                        case "/varys/proxy-wechat-mp/instance/wechatMp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("instanceWechatMpResp");

                        case "/varys/proxy-wechat-mp/instance/wechatMpParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("instanceWechatMpParamResp");

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

            executable.execute();
        }
    }
}

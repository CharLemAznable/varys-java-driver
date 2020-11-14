package com.github.charlemaznable.varystest.mock;

import com.github.charlemaznable.varys.resp.ToutiaoAppTokenResp;
import com.github.charlemaznable.varys.resp.WechatAppTokenResp;
import com.github.charlemaznable.varys.resp.WechatCorpTokenResp;
import com.github.charlemaznable.varys.resp.WechatCorpTpAuthTokenResp;
import com.github.charlemaznable.varys.resp.WechatMpLoginResp;
import com.github.charlemaznable.varys.resp.WechatTpAuthTokenResp;
import com.github.charlemaznable.varys.resp.WechatTpTokenResp;
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
                            val resp1 = new WechatAppTokenResp();
                            resp1.setAppId("1000");
                            resp1.setToken("instanceToken");
                            return new MockResponse().setBody(json(resp1));

                        case "/varys/query-wechat-tp-token/instance":
                            val resp2 = new WechatTpTokenResp();
                            resp2.setAppId("1000");
                            resp2.setToken("instanceToken");
                            return new MockResponse().setBody(json(resp2));

                        case "/varys/query-wechat-tp-auth-token/instance/abcd":
                            val resp3 = new WechatTpAuthTokenResp();
                            resp3.setAppId("1000");
                            resp3.setAuthorizerAppId("abcd");
                            resp3.setToken("instanceToken");
                            return new MockResponse().setBody(json(resp3));

                        case "/varys/query-wechat-corp-token/instance":
                            val resp4 = new WechatCorpTokenResp();
                            resp4.setCorpId("10000");
                            resp4.setToken("instanceToken");
                            return new MockResponse().setBody(json(resp4));

                        case "/varys/query-wechat-corp-tp-auth-token/instance/xyz":
                            val resp5 = new WechatCorpTpAuthTokenResp();
                            resp5.setCorpId("10000");
                            resp5.setSuiteId("xyz");
                            resp5.setToken("instanceToken");
                            return new MockResponse().setBody(json(resp5));

                        case "/varys/query-toutiao-app-token/instance":
                            val resp6 = new ToutiaoAppTokenResp();
                            resp6.setAppId("2000");
                            resp6.setToken("instanceToken");
                            return new MockResponse().setBody(json(resp6));

                        case "/varys/proxy-wechat-mp-login/instance?js_code=JSCODE":
                            val resp7 = new WechatMpLoginResp();
                            resp7.setOpenId("openid");
                            resp7.setSessionKey("session_key");
                            resp7.setUnionId("unionid");
                            resp7.setErrcode(0);
                            resp7.setErrmsg("OK");
                            return new MockResponse().setBody(json(resp7));
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

                        case "/varys/proxy-wechat-tp/instance/wechatTp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("instanceWechatTpResp");

                        case "/varys/proxy-wechat-tp/instance/wechatTpParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("instanceWechatTpParamResp");

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

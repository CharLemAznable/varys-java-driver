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

public class InterfaceConfigMock {

    @SneakyThrows
    public static void queryInterfaceConfig(Executable executable) {
        try (val mockWebServer = new MockWebServer()) {
            mockWebServer.setDispatcher(new Dispatcher() {
                @Override
                public MockResponse dispatch(RecordedRequest request) {
                    switch (request.getPath()) {
                        case "/varys/query-wechat-app-token/interface":
                            val resp1 = new WechatAppTokenResp();
                            resp1.setAppId("1000");
                            resp1.setToken("interfaceToken");
                            return new MockResponse().setBody(json(resp1));

                        case "/varys/query-wechat-tp-token/interface":
                            val resp2 = new WechatTpTokenResp();
                            resp2.setAppId("1000");
                            resp2.setToken("interfaceToken");
                            return new MockResponse().setBody(json(resp2));

                        case "/varys/query-wechat-tp-auth-token/interface/abcd":
                            val resp3 = new WechatTpAuthTokenResp();
                            resp3.setAppId("1000");
                            resp3.setAuthorizerAppId("abcd");
                            resp3.setToken("interfaceToken");
                            return new MockResponse().setBody(json(resp3));

                        case "/varys/query-wechat-corp-token/interface":
                            val resp4 = new WechatCorpTokenResp();
                            resp4.setCorpId("10000");
                            resp4.setToken("interfaceToken");
                            return new MockResponse().setBody(json(resp4));

                        case "/varys/query-wechat-corp-tp-auth-token/interface/xyz":
                            val resp5 = new WechatCorpTpAuthTokenResp();
                            resp5.setCorpId("10000");
                            resp5.setSuiteId("xyz");
                            resp5.setToken("interfaceToken");
                            return new MockResponse().setBody(json(resp5));

                        case "/varys/query-toutiao-app-token/interface":
                            val resp6 = new ToutiaoAppTokenResp();
                            resp6.setAppId("2000");
                            resp6.setToken("interfaceToken");
                            return new MockResponse().setBody(json(resp6));

                        case "/varys/proxy-wechat-mp-login/interface?js_code=JSCODE":
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
    public static void proxyInterfaceConfig(Executable executable) {
        try (val mockWebServer = new MockWebServer()) {
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

                        case "/varys/proxy-wechat-mp/interface/wechatMp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("interfaceWechatMpResp");

                        case "/varys/proxy-wechat-mp/interface/wechatMpParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("interfaceWechatMpParamResp");

                        case "/varys/proxy-wechat-tp/interface/wechatTp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("interfaceWechatTpResp");

                        case "/varys/proxy-wechat-tp/interface/wechatTpParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("interfaceWechatTpParamResp");

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

            executable.execute();
        }
    }
}

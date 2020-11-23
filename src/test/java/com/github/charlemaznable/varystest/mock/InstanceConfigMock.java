package com.github.charlemaznable.varystest.mock;

import com.github.charlemaznable.varys.resp.FengniaoAppTokenResp;
import com.github.charlemaznable.varys.resp.ToutiaoAppTokenResp;
import com.github.charlemaznable.varys.resp.WechatAppTokenResp;
import com.github.charlemaznable.varys.resp.WechatCorpTokenResp;
import com.github.charlemaznable.varys.resp.WechatCorpTpAuthTokenResp;
import com.github.charlemaznable.varys.resp.WechatJsConfigResp;
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
                            val wechatAppTokenResp = new WechatAppTokenResp();
                            wechatAppTokenResp.setAppId("1000");
                            wechatAppTokenResp.setToken("instanceToken");
                            wechatAppTokenResp.setTicket("instanceTicket");
                            return new MockResponse().setBody(json(wechatAppTokenResp));

                        case "/varys/proxy-wechat-mp-login/instance?js_code=JSCODE":
                            val wechatMpLoginResp = new WechatMpLoginResp();
                            wechatMpLoginResp.setOpenId("openid");
                            wechatMpLoginResp.setSessionKey("session_key");
                            wechatMpLoginResp.setUnionId("unionid");
                            wechatMpLoginResp.setErrcode(0);
                            wechatMpLoginResp.setErrmsg("OK");
                            return new MockResponse().setBody(json(wechatMpLoginResp));

                        case "/varys/query-wechat-app-js-config/instance?url=URL":
                            val wechatAppJsConfigResp = new WechatJsConfigResp();
                            wechatAppJsConfigResp.setAppId("1000");
                            wechatAppJsConfigResp.setNonceStr("nonceStr");
                            wechatAppJsConfigResp.setTimestamp(1000);
                            wechatAppJsConfigResp.setSignature("signature");
                            return new MockResponse().setBody(json(wechatAppJsConfigResp));

                        case "/varys/query-wechat-tp-token/instance":
                            val wechatTpTokenResp = new WechatTpTokenResp();
                            wechatTpTokenResp.setAppId("1000");
                            wechatTpTokenResp.setToken("instanceToken");
                            return new MockResponse().setBody(json(wechatTpTokenResp));

                        case "/varys/query-wechat-tp-auth-token/instance/abcd":
                            val wechatTpAuthTokenResp = new WechatTpAuthTokenResp();
                            wechatTpAuthTokenResp.setAppId("1000");
                            wechatTpAuthTokenResp.setAuthorizerAppId("abcd");
                            wechatTpAuthTokenResp.setToken("instanceToken");
                            wechatTpAuthTokenResp.setTicket("instanceTicket");
                            return new MockResponse().setBody(json(wechatTpAuthTokenResp));

                        case "/varys/query-wechat-tp-auth-js-config/instance?url=URL":
                            val wechatTpAuthJsConfigResp = new WechatJsConfigResp();
                            wechatTpAuthJsConfigResp.setAppId("abcd");
                            wechatTpAuthJsConfigResp.setNonceStr("nonceStr");
                            wechatTpAuthJsConfigResp.setTimestamp(1000);
                            wechatTpAuthJsConfigResp.setSignature("signature");
                            return new MockResponse().setBody(json(wechatTpAuthJsConfigResp));

                        case "/varys/query-wechat-corp-token/instance":
                            val wechatCorpTokenResp = new WechatCorpTokenResp();
                            wechatCorpTokenResp.setCorpId("10000");
                            wechatCorpTokenResp.setToken("instanceToken");
                            return new MockResponse().setBody(json(wechatCorpTokenResp));

                        case "/varys/query-wechat-corp-tp-auth-token/instance/xyz":
                            val wechatCorpTpAuthTokenResp = new WechatCorpTpAuthTokenResp();
                            wechatCorpTpAuthTokenResp.setCorpId("10000");
                            wechatCorpTpAuthTokenResp.setSuiteId("xyz");
                            wechatCorpTpAuthTokenResp.setToken("instanceToken");
                            return new MockResponse().setBody(json(wechatCorpTpAuthTokenResp));

                        case "/varys/query-toutiao-app-token/instance":
                            val toutiaoAppTokenResp = new ToutiaoAppTokenResp();
                            toutiaoAppTokenResp.setAppId("2000");
                            toutiaoAppTokenResp.setToken("instanceToken");
                            return new MockResponse().setBody(json(toutiaoAppTokenResp));

                        case "/varys/query-fengniao-app-token/instance":
                            val fengniaoAppTokenResp = new FengniaoAppTokenResp();
                            fengniaoAppTokenResp.setAppId("3000");
                            fengniaoAppTokenResp.setToken("instanceToken");
                            return new MockResponse().setBody(json(fengniaoAppTokenResp));
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

                        case "/varys/proxy-wechat-tp-auth/instance/abcd/wechatTpAuth":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("instanceWechatTpAuthResp");

                        case "/varys/proxy-wechat-tp-auth/instance/abcd/wechatTpAuthParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("instanceWechatTpAuthParamResp");

                        case "/varys/proxy-wechat-corp/instance/wechatCorp?a=b":
                            return new MockResponse().setBody("instanceWechatCorpResp");

                        case "/varys/proxy-wechat-corp/instance/wechatCorpParam/testParam":
                            assertEquals("a=b", request.getBody().readUtf8());
                            return new MockResponse().setBody("instanceWechatCorpParamResp");

                        case "/varys/proxy-fengniao-app/instance/fengniaoApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("instanceFengniaoAppResp");

                        case "/varys/proxy-fengniao-app/instance/fengniaoAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("instanceFengniaoAppParamResp");
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

package com.github.charlemaznable.varystest.mock;

import com.github.charlemaznable.varys.resp.FengniaoAppTokenResp;
import com.github.charlemaznable.varys.resp.ToutiaoAppTokenResp;
import com.github.charlemaznable.varys.resp.WechatAppMpLoginResp;
import com.github.charlemaznable.varys.resp.WechatAppTokenResp;
import com.github.charlemaznable.varys.resp.WechatCorpTokenResp;
import com.github.charlemaznable.varys.resp.WechatCorpTpAuthTokenResp;
import com.github.charlemaznable.varys.resp.WechatJsConfigResp;
import com.github.charlemaznable.varys.resp.WechatTpAuthMpLoginResp;
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
                            val wechatAppTokenResp = new WechatAppTokenResp();
                            wechatAppTokenResp.setAppId("1000");
                            wechatAppTokenResp.setToken("interfaceToken");
                            wechatAppTokenResp.setTicket("interfaceTicket");
                            return new MockResponse().setBody(json(wechatAppTokenResp));

                        case "/varys/proxy-wechat-app-mp-login/interface?js_code=JSCODE":
                            val wechatAppMpLoginResp = new WechatAppMpLoginResp();
                            wechatAppMpLoginResp.setOpenId("openid");
                            wechatAppMpLoginResp.setSessionKey("session_key");
                            wechatAppMpLoginResp.setUnionId("unionid");
                            wechatAppMpLoginResp.setErrcode(0);
                            wechatAppMpLoginResp.setErrmsg("OK");
                            return new MockResponse().setBody(json(wechatAppMpLoginResp));

                        case "/varys/query-wechat-app-js-config/interface?url=URL":
                            val wechatAppJsConfigResp = new WechatJsConfigResp();
                            wechatAppJsConfigResp.setAppId("1000");
                            wechatAppJsConfigResp.setNonceStr("nonceStr");
                            wechatAppJsConfigResp.setTimestamp(1000);
                            wechatAppJsConfigResp.setSignature("signature");
                            return new MockResponse().setBody(json(wechatAppJsConfigResp));

                        case "/varys/query-wechat-tp-token/interface":
                            val wechatTpTokenResp = new WechatTpTokenResp();
                            wechatTpTokenResp.setAppId("1000");
                            wechatTpTokenResp.setToken("interfaceToken");
                            return new MockResponse().setBody(json(wechatTpTokenResp));

                        case "/varys/query-wechat-tp-auth-token/interface/abcd":
                            val wechatTpAuthTokenResp = new WechatTpAuthTokenResp();
                            wechatTpAuthTokenResp.setAppId("1000");
                            wechatTpAuthTokenResp.setAuthorizerAppId("abcd");
                            wechatTpAuthTokenResp.setToken("interfaceToken");
                            wechatTpAuthTokenResp.setTicket("interfaceTicket");
                            return new MockResponse().setBody(json(wechatTpAuthTokenResp));

                        case "/varys/proxy-wechat-tp-auth-mp-login/interface/abcd?js_code=JSCODE":
                            val wechatTpAuthMpLoginResp = new WechatTpAuthMpLoginResp();
                            wechatTpAuthMpLoginResp.setOpenId("openid");
                            wechatTpAuthMpLoginResp.setSessionKey("session_key");
                            wechatTpAuthMpLoginResp.setErrcode(0);
                            wechatTpAuthMpLoginResp.setErrmsg("OK");
                            return new MockResponse().setBody(json(wechatTpAuthMpLoginResp));

                        case "/varys/query-wechat-tp-auth-js-config/interface/abcd?url=URL":
                            val wechatTpAuthJsConfigResp = new WechatJsConfigResp();
                            wechatTpAuthJsConfigResp.setAppId("abcd");
                            wechatTpAuthJsConfigResp.setNonceStr("nonceStr");
                            wechatTpAuthJsConfigResp.setTimestamp(1000);
                            wechatTpAuthJsConfigResp.setSignature("signature");
                            return new MockResponse().setBody(json(wechatTpAuthJsConfigResp));

                        case "/varys/query-wechat-corp-token/interface":
                            val wechatCorpTokenResp = new WechatCorpTokenResp();
                            wechatCorpTokenResp.setCorpId("10000");
                            wechatCorpTokenResp.setToken("interfaceToken");
                            return new MockResponse().setBody(json(wechatCorpTokenResp));

                        case "/varys/query-wechat-corp-tp-auth-token/interface/xyz":
                            val wechatCorpTpAuthTokenResp = new WechatCorpTpAuthTokenResp();
                            wechatCorpTpAuthTokenResp.setCorpId("10000");
                            wechatCorpTpAuthTokenResp.setSuiteId("xyz");
                            wechatCorpTpAuthTokenResp.setToken("interfaceToken");
                            return new MockResponse().setBody(json(wechatCorpTpAuthTokenResp));

                        case "/varys/query-toutiao-app-token/interface":
                            val toutiaoAppTokenResp = new ToutiaoAppTokenResp();
                            toutiaoAppTokenResp.setAppId("2000");
                            toutiaoAppTokenResp.setToken("interfaceToken");
                            return new MockResponse().setBody(json(toutiaoAppTokenResp));

                        case "/varys/query-fengniao-app-token/interface":
                            val fengniaoAppTokenResp = new FengniaoAppTokenResp();
                            fengniaoAppTokenResp.setAppId("3000");
                            fengniaoAppTokenResp.setToken("interfaceToken");
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

                        case "/varys/proxy-wechat-tp/interface/wechatTp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("interfaceWechatTpResp");

                        case "/varys/proxy-wechat-tp/interface/wechatTpParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("interfaceWechatTpParamResp");

                        case "/varys/proxy-wechat-tp-auth/interface/abcd/wechatTpAuth":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("interfaceWechatTpAuthResp");

                        case "/varys/proxy-wechat-tp-auth/interface/abcd/wechatTpAuthParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("interfaceWechatTpAuthParamResp");

                        case "/varys/proxy-wechat-corp/interface/wechatCorp?a=b":
                            return new MockResponse().setBody("interfaceWechatCorpResp");

                        case "/varys/proxy-wechat-corp/interface/wechatCorpParam/testParam":
                            assertEquals("a=b", request.getBody().readUtf8());
                            return new MockResponse().setBody("interfaceWechatCorpParamResp");

                        case "/varys/proxy-fengniao-app/interface/fengniaoApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("interfaceFengniaoAppResp");

                        case "/varys/proxy-fengniao-app/interface/fengniaoAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("interfaceFengniaoAppParamResp");
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

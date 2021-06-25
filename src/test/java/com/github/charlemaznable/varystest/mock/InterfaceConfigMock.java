package com.github.charlemaznable.varystest.mock;

import com.github.charlemaznable.varys.impl.Query;
import com.github.charlemaznable.varys.resp.FengniaoAppTokenResp;
import com.github.charlemaznable.varys.resp.ShansongAppTokenResp;
import com.github.charlemaznable.varys.resp.ToutiaoAppTokenResp;
import com.github.charlemaznable.varys.resp.WechatAppMpLoginResp;
import com.github.charlemaznable.varys.resp.WechatAppTokenResp;
import com.github.charlemaznable.varys.resp.WechatCorpTokenResp;
import com.github.charlemaznable.varys.resp.WechatCorpTpAuthTokenResp;
import com.github.charlemaznable.varys.resp.WechatJsConfigResp;
import com.github.charlemaznable.varys.resp.WechatTpAuthMpLoginResp;
import com.github.charlemaznable.varys.resp.WechatTpAuthTokenResp;
import com.github.charlemaznable.varys.resp.WechatTpTokenResp;
import com.github.charlemaznable.varystest.proxy.ProxyFengniaoAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyShansongAppDeveloperDemo;
import com.github.charlemaznable.varystest.proxy.ProxyShansongAppFileDemo;
import com.github.charlemaznable.varystest.proxy.ProxyShansongAppMerchantDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatAppDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatCorpDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpAuthDemo;
import com.github.charlemaznable.varystest.proxy.ProxyWechatTpDemo;
import lombok.SneakyThrows;
import lombok.val;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.springframework.http.HttpStatus;

import static com.github.charlemaznable.core.codec.Json.json;
import static com.github.charlemaznable.core.codec.Json.jsonOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterfaceConfigMock {

    @SneakyThrows
    public static void queryInterfaceConfig(Query query) {
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

                        case "/varys/query-fengniao-app-token/interface/xyz":
                            val fengniaoAppTokenResp = new FengniaoAppTokenResp();
                            fengniaoAppTokenResp.setAppId("3000");
                            fengniaoAppTokenResp.setMerchantId("xyz");
                            fengniaoAppTokenResp.setToken("interfaceToken");
                            return new MockResponse().setBody(json(fengniaoAppTokenResp));

                        case "/varys/query-shansong-app-token/interface/xyz":
                            val shansongAppTokenResp = new ShansongAppTokenResp();
                            shansongAppTokenResp.setAppId("4000");
                            shansongAppTokenResp.setMerchantCode("xyz");
                            shansongAppTokenResp.setToken("interfaceToken");
                            return new MockResponse().setBody(json(shansongAppTokenResp));
                    }
                    return new MockResponse()
                            .setResponseCode(HttpStatus.NOT_FOUND.value())
                            .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
                }
            });
            mockWebServer.start(4236);

            val wechatAppTokenResp = query.wechatAppToken("interface");
            assertEquals("1000", wechatAppTokenResp.getAppId());
            assertEquals("interfaceToken", wechatAppTokenResp.getToken());
            assertEquals("interfaceTicket", wechatAppTokenResp.getTicket());

            val wechatAppMpLoginResp = query.wechatAppMpLogin("interface", "JSCODE");
            assertEquals("openid", wechatAppMpLoginResp.getOpenId());
            assertEquals("session_key", wechatAppMpLoginResp.getSessionKey());
            assertEquals("unionid", wechatAppMpLoginResp.getUnionId());
            assertEquals(0, wechatAppMpLoginResp.getErrcode());
            assertEquals("OK", wechatAppMpLoginResp.getErrmsg());

            val wechatAppJsConfigResp = query.wechatAppJsConfig("interface", "URL");
            assertEquals("1000", wechatAppJsConfigResp.getAppId());
            assertEquals("nonceStr", wechatAppJsConfigResp.getNonceStr());
            assertEquals(1000, wechatAppJsConfigResp.getTimestamp());
            assertEquals("signature", wechatAppJsConfigResp.getSignature());

            val wechatTpTokenResp = query.wechatTpToken("interface");
            assertEquals("1000", wechatTpTokenResp.getAppId());
            assertEquals("interfaceToken", wechatTpTokenResp.getToken());

            val wechatTpAuthTokenResp = query.wechatTpAuthToken("interface", "abcd");
            assertEquals("1000", wechatTpAuthTokenResp.getAppId());
            assertEquals("abcd", wechatTpAuthTokenResp.getAuthorizerAppId());
            assertEquals("interfaceToken", wechatTpAuthTokenResp.getToken());
            assertEquals("interfaceTicket", wechatTpAuthTokenResp.getTicket());

            val wechatTpAuthMpLoginResp = query.wechatTpAuthMpLogin("interface", "abcd", "JSCODE");
            assertEquals("openid", wechatTpAuthMpLoginResp.getOpenId());
            assertEquals("session_key", wechatTpAuthMpLoginResp.getSessionKey());
            assertEquals(0, wechatTpAuthMpLoginResp.getErrcode());
            assertEquals("OK", wechatTpAuthMpLoginResp.getErrmsg());

            val wechatTpAuthJsConfigResp = query.wechatTpAuthJsConfig("interface", "abcd", "URL");
            assertEquals("abcd", wechatTpAuthJsConfigResp.getAppId());
            assertEquals("nonceStr", wechatTpAuthJsConfigResp.getNonceStr());
            assertEquals(1000, wechatTpAuthJsConfigResp.getTimestamp());
            assertEquals("signature", wechatTpAuthJsConfigResp.getSignature());

            val wechatCorpTokenResp = query.wechatCorpToken("interface");
            assertEquals("10000", wechatCorpTokenResp.getCorpId());
            assertEquals("interfaceToken", wechatCorpTokenResp.getToken());

            val wechatCorpTpAuthTokenResp = query.wechatCorpTpAuthToken("interface", "xyz");
            assertEquals("10000", wechatCorpTpAuthTokenResp.getCorpId());
            assertEquals("xyz", wechatCorpTpAuthTokenResp.getSuiteId());
            assertEquals("interfaceToken", wechatCorpTpAuthTokenResp.getToken());

            val toutiaoAppTokenResp = query.toutiaoAppToken("interface");
            assertEquals("2000", toutiaoAppTokenResp.getAppId());
            assertEquals("interfaceToken", toutiaoAppTokenResp.getToken());

            val fengniaoAppTokenResp = query.fengniaoAppToken("interface", "xyz");
            assertEquals("3000", fengniaoAppTokenResp.getAppId());
            assertEquals("xyz", fengniaoAppTokenResp.getMerchantId());
            assertEquals("interfaceToken", fengniaoAppTokenResp.getToken());

            val shansongAppTokenResp = query.shansongAppToken("interface", "xyz");
            assertEquals("4000", shansongAppTokenResp.getAppId());
            assertEquals("xyz", shansongAppTokenResp.getMerchantCode());
            assertEquals("interfaceToken", shansongAppTokenResp.getToken());
        }
    }

    @SneakyThrows
    public static void proxyInterfaceConfig(ProxyWechatAppDemo proxyWechatApp,
                                            ProxyWechatTpDemo proxyWechatTp,
                                            ProxyWechatTpAuthDemo proxyWechatTpAuth,
                                            ProxyWechatCorpDemo proxyWechatCorp,
                                            ProxyFengniaoAppDemo proxyFengniaoApp,
                                            ProxyShansongAppDeveloperDemo proxyShansongAppDeveloper,
                                            ProxyShansongAppMerchantDemo proxyShansongAppMerchant,
                                            ProxyShansongAppFileDemo proxyShansongAppFile) {
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

                        case "/varys/proxy-fengniao-app/interface/xyz/fengniaoApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("interfaceFengniaoAppResp");

                        case "/varys/proxy-fengniao-app/interface/xyz/fengniaoAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("interfaceFengniaoAppParamResp");

                        case "/varys/proxy-shansong-app-developer/interface/xyz/shansongApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("interfaceShansongAppResp");

                        case "/varys/proxy-shansong-app-developer/interface/xyz/shansongAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("interfaceShansongAppParamResp");

                        case "/varys/proxy-shansong-app-merchant/interface/shansongApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("interfaceShansongAppResp");

                        case "/varys/proxy-shansong-app-merchant/interface/shansongAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("interfaceShansongAppParamResp");

                        case "/varys/proxy-shansong-app-file/interface/shansongApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("interfaceShansongAppResp");

                        case "/varys/proxy-shansong-app-file/interface/shansongAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("interfaceShansongAppParamResp");
                    }
                    return new MockResponse()
                            .setResponseCode(HttpStatus.NOT_FOUND.value())
                            .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
                }
            });
            mockWebServer.start(4236);

            val wechatAppResp = proxyWechatApp.wechatApp("interface", "b");
            assertEquals("interfaceWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyWechatApp.wechatAppParam("interface", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceWechatAppParamResp", wechatAppParamResp);

            val wechatTpResp = proxyWechatTp.wechatTp("interface", "b");
            assertEquals("interfaceWechatTpResp", wechatTpResp);

            val wechatTpParamResp = proxyWechatTp.wechatTpParam("interface", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceWechatTpParamResp", wechatTpParamResp);

            val wechatTpAuthResp = proxyWechatTpAuth.wechatTpAuth("interface", "abcd", "b");
            assertEquals("interfaceWechatTpAuthResp", wechatTpAuthResp);

            val wechatTpAuthParamResp = proxyWechatTpAuth.wechatTpAuthParam("interface", "abcd", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceWechatTpAuthParamResp", wechatTpAuthParamResp);

            val wechatCorpResp = proxyWechatCorp.wechatCorp("interface", "b");
            assertEquals("interfaceWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyWechatCorp.wechatCorpParam("interface", "testParam", "b");
            assertEquals("interfaceWechatCorpParamResp", wechatCorpParamResp);

            val fengniaoAppResp = proxyFengniaoApp.fengniaoApp("interface", "xyz", "b");
            assertEquals("interfaceFengniaoAppResp", fengniaoAppResp);

            val fengniaoAppParamResp = proxyFengniaoApp.fengniaoAppParam("interface", "xyz", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceFengniaoAppParamResp", fengniaoAppParamResp);

            val shansongAppDeveloperResp = proxyShansongAppDeveloper.shansongApp("interface", "xyz", "b");
            assertEquals("interfaceShansongAppResp", shansongAppDeveloperResp);

            val shansongAppDeveloperParamResp = proxyShansongAppDeveloper.shansongAppParam("interface", "xyz", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceShansongAppParamResp", shansongAppDeveloperParamResp);

            val shansongAppMerchantResp = proxyShansongAppMerchant.shansongApp("interface", "b");
            assertEquals("interfaceShansongAppResp", shansongAppMerchantResp);

            val shansongAppMerchantParamResp = proxyShansongAppMerchant.shansongAppParam("interface", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceShansongAppParamResp", shansongAppMerchantParamResp);

            val shansongAppFileResp = proxyShansongAppFile.shansongApp("interface", "b");
            assertEquals("interfaceShansongAppResp", shansongAppFileResp);

            val shansongAppFileParamResp = proxyShansongAppFile.shansongAppParam("interface", "testParam", jsonOf("a", "b"));
            assertEquals("interfaceShansongAppParamResp", shansongAppFileParamResp);
        }
    }
}

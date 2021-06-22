package com.github.charlemaznable.varystest.mock;

import com.github.charlemaznable.core.lang.EverythingIsNonNull;
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
import static com.github.charlemaznable.core.lang.Condition.checkNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultConfigMock {

    @EverythingIsNonNull
    @SneakyThrows
    public static void queryDefaultConfig(Query query) {
        try (val mockWebServer = new MockWebServer()) {
            mockWebServer.setDispatcher(new Dispatcher() {
                @Override
                public MockResponse dispatch(RecordedRequest request) {
                    switch (checkNotNull(request.getPath())) {
                        case "/varys/query-wechat-app-token/default":
                            val wechatAppTokenResp = new WechatAppTokenResp();
                            wechatAppTokenResp.setAppId("1000");
                            wechatAppTokenResp.setToken("defaultToken");
                            wechatAppTokenResp.setTicket("defaultTicket");
                            return new MockResponse().setBody(json(wechatAppTokenResp));

                        case "/varys/proxy-wechat-app-mp-login/default?js_code=JSCODE":
                            val wechatAppMpLoginResp = new WechatAppMpLoginResp();
                            wechatAppMpLoginResp.setOpenId("openid");
                            wechatAppMpLoginResp.setSessionKey("session_key");
                            wechatAppMpLoginResp.setUnionId("unionid");
                            wechatAppMpLoginResp.setErrcode(0);
                            wechatAppMpLoginResp.setErrmsg("OK");
                            return new MockResponse().setBody(json(wechatAppMpLoginResp));

                        case "/varys/query-wechat-app-js-config/default?url=URL":
                            val wechatAppJsConfigResp = new WechatJsConfigResp();
                            wechatAppJsConfigResp.setAppId("1000");
                            wechatAppJsConfigResp.setNonceStr("nonceStr");
                            wechatAppJsConfigResp.setTimestamp(1000);
                            wechatAppJsConfigResp.setSignature("signature");
                            return new MockResponse().setBody(json(wechatAppJsConfigResp));

                        case "/varys/query-wechat-tp-token/default":
                            val wechatTpTokenResp = new WechatTpTokenResp();
                            wechatTpTokenResp.setAppId("1000");
                            wechatTpTokenResp.setToken("defaultToken");
                            return new MockResponse().setBody(json(wechatTpTokenResp));

                        case "/varys/query-wechat-tp-auth-token/default/abcd":
                            val wechatTpAuthTokenResp = new WechatTpAuthTokenResp();
                            wechatTpAuthTokenResp.setAppId("1000");
                            wechatTpAuthTokenResp.setAuthorizerAppId("abcd");
                            wechatTpAuthTokenResp.setToken("defaultToken");
                            wechatTpAuthTokenResp.setTicket("defaultTicket");
                            return new MockResponse().setBody(json(wechatTpAuthTokenResp));

                        case "/varys/proxy-wechat-tp-auth-mp-login/default/abcd?js_code=JSCODE":
                            val wechatTpAuthMpLoginResp = new WechatTpAuthMpLoginResp();
                            wechatTpAuthMpLoginResp.setOpenId("openid");
                            wechatTpAuthMpLoginResp.setSessionKey("session_key");
                            wechatTpAuthMpLoginResp.setErrcode(0);
                            wechatTpAuthMpLoginResp.setErrmsg("OK");
                            return new MockResponse().setBody(json(wechatTpAuthMpLoginResp));

                        case "/varys/query-wechat-tp-auth-js-config/default/abcd?url=URL":
                            val wechatTpAuthJsConfigResp = new WechatJsConfigResp();
                            wechatTpAuthJsConfigResp.setAppId("abcd");
                            wechatTpAuthJsConfigResp.setNonceStr("nonceStr");
                            wechatTpAuthJsConfigResp.setTimestamp(1000);
                            wechatTpAuthJsConfigResp.setSignature("signature");
                            return new MockResponse().setBody(json(wechatTpAuthJsConfigResp));

                        case "/varys/query-wechat-corp-token/default":
                            val wechatCorpTokenResp = new WechatCorpTokenResp();
                            wechatCorpTokenResp.setCorpId("10000");
                            wechatCorpTokenResp.setToken("defaultToken");
                            return new MockResponse().setBody(json(wechatCorpTokenResp));

                        case "/varys/query-wechat-corp-tp-auth-token/default/xyz":
                            val wechatCorpTpAuthTokenResp = new WechatCorpTpAuthTokenResp();
                            wechatCorpTpAuthTokenResp.setCorpId("10000");
                            wechatCorpTpAuthTokenResp.setSuiteId("xyz");
                            wechatCorpTpAuthTokenResp.setToken("defaultToken");
                            return new MockResponse().setBody(json(wechatCorpTpAuthTokenResp));

                        case "/varys/query-toutiao-app-token/default":
                            val toutiaoAppTokenResp = new ToutiaoAppTokenResp();
                            toutiaoAppTokenResp.setAppId("2000");
                            toutiaoAppTokenResp.setToken("defaultToken");
                            return new MockResponse().setBody(json(toutiaoAppTokenResp));

                        case "/varys/query-fengniao-app-token/default/xyz":
                            val fengniaoAppTokenResp = new FengniaoAppTokenResp();
                            fengniaoAppTokenResp.setAppId("3000");
                            fengniaoAppTokenResp.setMerchantId("xyz");
                            fengniaoAppTokenResp.setToken("defaultToken");
                            return new MockResponse().setBody(json(fengniaoAppTokenResp));

                        case "/varys/query-shansong-app-token/default/xyz":
                            val shansongAppTokenResp = new ShansongAppTokenResp();
                            shansongAppTokenResp.setAppId("4000");
                            shansongAppTokenResp.setMerchantCode("xyz");
                            shansongAppTokenResp.setToken("defaultToken");
                            return new MockResponse().setBody(json(shansongAppTokenResp));
                    }
                    return new MockResponse()
                            .setResponseCode(HttpStatus.NOT_FOUND.value())
                            .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
                }
            });
            mockWebServer.start(4236);

            val wechatAppTokenResp = query.wechatAppToken("default");
            assertEquals("1000", wechatAppTokenResp.getAppId());
            assertEquals("defaultToken", wechatAppTokenResp.getToken());
            assertEquals("defaultTicket", wechatAppTokenResp.getTicket());

            val wechatAppMpLoginResp = query.wechatAppMpLogin("default", "JSCODE");
            assertEquals("openid", wechatAppMpLoginResp.getOpenId());
            assertEquals("session_key", wechatAppMpLoginResp.getSessionKey());
            assertEquals("unionid", wechatAppMpLoginResp.getUnionId());
            assertEquals(0, wechatAppMpLoginResp.getErrcode());
            assertEquals("OK", wechatAppMpLoginResp.getErrmsg());

            val wechatAppJsConfigResp = query.wechatAppJsConfig("default", "URL");
            assertEquals("1000", wechatAppJsConfigResp.getAppId());
            assertEquals("nonceStr", wechatAppJsConfigResp.getNonceStr());
            assertEquals(1000, wechatAppJsConfigResp.getTimestamp());
            assertEquals("signature", wechatAppJsConfigResp.getSignature());

            val wechatTpTokenResp = query.wechatTpToken("default");
            assertEquals("1000", wechatTpTokenResp.getAppId());
            assertEquals("defaultToken", wechatTpTokenResp.getToken());

            val wechatTpAuthTokenResp = query.wechatTpAuthToken("default", "abcd");
            assertEquals("1000", wechatTpAuthTokenResp.getAppId());
            assertEquals("abcd", wechatTpAuthTokenResp.getAuthorizerAppId());
            assertEquals("defaultToken", wechatTpAuthTokenResp.getToken());
            assertEquals("defaultTicket", wechatTpAuthTokenResp.getTicket());

            val wechatTpAuthMpLoginResp = query.wechatTpAuthMpLogin("default", "abcd", "JSCODE");
            assertEquals("openid", wechatTpAuthMpLoginResp.getOpenId());
            assertEquals("session_key", wechatTpAuthMpLoginResp.getSessionKey());
            assertEquals(0, wechatTpAuthMpLoginResp.getErrcode());
            assertEquals("OK", wechatTpAuthMpLoginResp.getErrmsg());

            val wechatTpAuthJsConfigResp = query.wechatTpAuthJsConfig("default", "abcd", "URL");
            assertEquals("abcd", wechatTpAuthJsConfigResp.getAppId());
            assertEquals("nonceStr", wechatTpAuthJsConfigResp.getNonceStr());
            assertEquals(1000, wechatTpAuthJsConfigResp.getTimestamp());
            assertEquals("signature", wechatTpAuthJsConfigResp.getSignature());

            val wechatCorpTokenResp = query.wechatCorpToken("default");
            assertEquals("10000", wechatCorpTokenResp.getCorpId());
            assertEquals("defaultToken", wechatCorpTokenResp.getToken());

            val wechatCorpTpAuthTokenResp = query.wechatCorpTpAuthToken("default", "xyz");
            assertEquals("10000", wechatCorpTpAuthTokenResp.getCorpId());
            assertEquals("xyz", wechatCorpTpAuthTokenResp.getSuiteId());
            assertEquals("defaultToken", wechatCorpTpAuthTokenResp.getToken());

            val toutiaoAppTokenResp = query.toutiaoAppToken("default");
            assertEquals("2000", toutiaoAppTokenResp.getAppId());
            assertEquals("defaultToken", toutiaoAppTokenResp.getToken());

            val fengniaoAppTokenResp = query.fengniaoAppToken("default", "xyz");
            assertEquals("3000", fengniaoAppTokenResp.getAppId());
            assertEquals("xyz", fengniaoAppTokenResp.getMerchantId());
            assertEquals("defaultToken", fengniaoAppTokenResp.getToken());

            val shansongAppTokenResp = query.shansongAppToken("default", "xyz");
            assertEquals("4000", shansongAppTokenResp.getAppId());
            assertEquals("xyz", shansongAppTokenResp.getMerchantCode());
            assertEquals("defaultToken", shansongAppTokenResp.getToken());
        }
    }

    @EverythingIsNonNull
    @SneakyThrows
    public static void proxyDefaultConfig(ProxyWechatAppDemo proxyWechatApp,
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
                    switch (checkNotNull(request.getPath())) {
                        case "/varys/proxy-wechat-app/default/wechatApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("defaultWechatAppResp");

                        case "/varys/proxy-wechat-app/default/wechatAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("defaultWechatAppParamResp");

                        case "/varys/proxy-wechat-tp/default/wechatTp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("defaultWechatTpResp");

                        case "/varys/proxy-wechat-tp/default/wechatTpParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("defaultWechatTpParamResp");

                        case "/varys/proxy-wechat-tp-auth/default/abcd/wechatTpAuth":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("defaultWechatTpAuthResp");

                        case "/varys/proxy-wechat-tp-auth/default/abcd/wechatTpAuthParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("defaultWechatTpAuthParamResp");

                        case "/varys/proxy-wechat-corp/default/wechatCorp?a=b":
                            return new MockResponse().setBody("defaultWechatCorpResp");

                        case "/varys/proxy-wechat-corp/default/wechatCorpParam/testParam":
                            assertEquals("a=b", request.getBody().readUtf8());
                            return new MockResponse().setBody("defaultWechatCorpParamResp");

                        case "/varys/proxy-fengniao-app/default/xyz/fengniaoApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("defaultFengniaoAppResp");

                        case "/varys/proxy-fengniao-app/default/xyz/fengniaoAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("defaultFengniaoAppParamResp");

                        case "/varys/proxy-shansong-app-developer/default/xyz/shansongApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("defaultShansongAppResp");

                        case "/varys/proxy-shansong-app-developer/default/xyz/shansongAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("defaultShansongAppParamResp");

                        case "/varys/proxy-shansong-app-merchant/default/shansongApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("defaultShansongAppResp");

                        case "/varys/proxy-shansong-app-merchant/default/shansongAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("defaultShansongAppParamResp");

                        case "/varys/proxy-shansong-app-file/default/shansongApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("defaultShansongAppResp");

                        case "/varys/proxy-shansong-app-file/default/shansongAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("defaultShansongAppParamResp");
                    }
                    return new MockResponse()
                            .setResponseCode(HttpStatus.NOT_FOUND.value())
                            .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
                }
            });
            mockWebServer.start(4236);

            val wechatAppResp = proxyWechatApp.wechatApp("default", "b");
            assertEquals("defaultWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyWechatApp.wechatAppParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatAppParamResp", wechatAppParamResp);

            val wechatTpResp = proxyWechatTp.wechatTp("default", "b");
            assertEquals("defaultWechatTpResp", wechatTpResp);

            val wechatTpParamResp = proxyWechatTp.wechatTpParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatTpParamResp", wechatTpParamResp);

            val wechatTpAuthResp = proxyWechatTpAuth.wechatTpAuth("default", "abcd", "b");
            assertEquals("defaultWechatTpAuthResp", wechatTpAuthResp);

            val wechatTpAuthParamResp = proxyWechatTpAuth.wechatTpAuthParam("default", "abcd", "testParam", jsonOf("a", "b"));
            assertEquals("defaultWechatTpAuthParamResp", wechatTpAuthParamResp);

            val wechatCorpResp = proxyWechatCorp.wechatCorp("default", "b");
            assertEquals("defaultWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyWechatCorp.wechatCorpParam("default", "testParam", "b");
            assertEquals("defaultWechatCorpParamResp", wechatCorpParamResp);

            val fengniaoAppResp = proxyFengniaoApp.fengniaoApp("default", "xyz", "b");
            assertEquals("defaultFengniaoAppResp", fengniaoAppResp);

            val fengniaoAppParamResp = proxyFengniaoApp.fengniaoAppParam("default", "xyz", "testParam", jsonOf("a", "b"));
            assertEquals("defaultFengniaoAppParamResp", fengniaoAppParamResp);

            val shansongAppDeveloperResp = proxyShansongAppDeveloper.shansongApp("default", "xyz", "b");
            assertEquals("defaultShansongAppResp", shansongAppDeveloperResp);

            val shansongAppDeveloperParamResp = proxyShansongAppDeveloper.shansongAppParam("default", "xyz", "testParam", jsonOf("a", "b"));
            assertEquals("defaultShansongAppParamResp", shansongAppDeveloperParamResp);

            val shansongAppMerchantResp = proxyShansongAppMerchant.shansongApp("default", "b");
            assertEquals("defaultShansongAppResp", shansongAppMerchantResp);

            val shansongAppMerchantParamResp = proxyShansongAppMerchant.shansongAppParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultShansongAppParamResp", shansongAppMerchantParamResp);

            val shansongAppFileResp = proxyShansongAppFile.shansongApp("default", "b");
            assertEquals("defaultShansongAppResp", shansongAppFileResp);

            val shansongAppFileParamResp = proxyShansongAppFile.shansongAppParam("default", "testParam", jsonOf("a", "b"));
            assertEquals("defaultShansongAppParamResp", shansongAppFileParamResp);
        }
    }
}

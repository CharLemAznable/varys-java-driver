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

public class InstanceConfigMock {

    @EverythingIsNonNull
    @SneakyThrows
    public static void queryInstanceConfig(Query query) {
        try (val mockWebServer = new MockWebServer()) {
            mockWebServer.setDispatcher(new Dispatcher() {
                @Override
                public MockResponse dispatch(RecordedRequest request) {
                    switch (checkNotNull(request.getPath())) {
                        case "/varys/query-wechat-app-token/instance":
                            val wechatAppTokenResp = new WechatAppTokenResp();
                            wechatAppTokenResp.setAppId("1000");
                            wechatAppTokenResp.setToken("instanceToken");
                            wechatAppTokenResp.setTicket("instanceTicket");
                            return new MockResponse().setBody(json(wechatAppTokenResp));

                        case "/varys/proxy-wechat-app-mp-login/instance?js_code=JSCODE":
                            val wechatAppMpLoginResp = new WechatAppMpLoginResp();
                            wechatAppMpLoginResp.setOpenId("openid");
                            wechatAppMpLoginResp.setSessionKey("session_key");
                            wechatAppMpLoginResp.setUnionId("unionid");
                            wechatAppMpLoginResp.setErrcode(0);
                            wechatAppMpLoginResp.setErrmsg("OK");
                            return new MockResponse().setBody(json(wechatAppMpLoginResp));

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

                        case "/varys/proxy-wechat-tp-auth-mp-login/instance/abcd?js_code=JSCODE":
                            val wechatTpAuthMpLoginResp = new WechatTpAuthMpLoginResp();
                            wechatTpAuthMpLoginResp.setOpenId("openid");
                            wechatTpAuthMpLoginResp.setSessionKey("session_key");
                            wechatTpAuthMpLoginResp.setErrcode(0);
                            wechatTpAuthMpLoginResp.setErrmsg("OK");
                            return new MockResponse().setBody(json(wechatTpAuthMpLoginResp));

                        case "/varys/query-wechat-tp-auth-js-config/instance/abcd?url=URL":
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

                        case "/varys/query-fengniao-app-token/instance/xyz":
                            val fengniaoAppTokenResp = new FengniaoAppTokenResp();
                            fengniaoAppTokenResp.setAppId("3000");
                            fengniaoAppTokenResp.setMerchantId("xyz");
                            fengniaoAppTokenResp.setToken("instanceToken");
                            return new MockResponse().setBody(json(fengniaoAppTokenResp));

                        case "/varys/query-shansong-app-token/instance/xyz":
                            val shansongAppTokenResp = new ShansongAppTokenResp();
                            shansongAppTokenResp.setAppId("4000");
                            shansongAppTokenResp.setMerchantCode("xyz");
                            shansongAppTokenResp.setToken("instanceToken");
                            return new MockResponse().setBody(json(shansongAppTokenResp));
                    }
                    return new MockResponse()
                            .setResponseCode(HttpStatus.NOT_FOUND.value())
                            .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
                }
            });
            mockWebServer.start(4236);

            val wechatAppTokenResp = query.wechatAppToken("instance");
            assertEquals("1000", wechatAppTokenResp.getAppId());
            assertEquals("instanceToken", wechatAppTokenResp.getToken());
            assertEquals("instanceTicket", wechatAppTokenResp.getTicket());

            val wechatAppMpLoginResp = query.wechatAppMpLogin("instance", "JSCODE");
            assertEquals("openid", wechatAppMpLoginResp.getOpenId());
            assertEquals("session_key", wechatAppMpLoginResp.getSessionKey());
            assertEquals("unionid", wechatAppMpLoginResp.getUnionId());
            assertEquals(0, wechatAppMpLoginResp.getErrcode());
            assertEquals("OK", wechatAppMpLoginResp.getErrmsg());

            val wechatAppJsConfigResp = query.wechatAppJsConfig("instance", "URL");
            assertEquals("1000", wechatAppJsConfigResp.getAppId());
            assertEquals("nonceStr", wechatAppJsConfigResp.getNonceStr());
            assertEquals(1000, wechatAppJsConfigResp.getTimestamp());
            assertEquals("signature", wechatAppJsConfigResp.getSignature());

            val wechatTpTokenResp = query.wechatTpToken("instance");
            assertEquals("1000", wechatTpTokenResp.getAppId());
            assertEquals("instanceToken", wechatTpTokenResp.getToken());

            val wechatTpAuthTokenResp = query.wechatTpAuthToken("instance", "abcd");
            assertEquals("1000", wechatTpAuthTokenResp.getAppId());
            assertEquals("abcd", wechatTpAuthTokenResp.getAuthorizerAppId());
            assertEquals("instanceToken", wechatTpAuthTokenResp.getToken());
            assertEquals("instanceTicket", wechatTpAuthTokenResp.getTicket());

            val wechatTpAuthMpLoginResp = query.wechatTpAuthMpLogin("instance", "abcd", "JSCODE");
            assertEquals("openid", wechatTpAuthMpLoginResp.getOpenId());
            assertEquals("session_key", wechatTpAuthMpLoginResp.getSessionKey());
            assertEquals(0, wechatTpAuthMpLoginResp.getErrcode());
            assertEquals("OK", wechatTpAuthMpLoginResp.getErrmsg());

            val wechatTpAuthJsConfigResp = query.wechatTpAuthJsConfig("instance", "abcd", "URL");
            assertEquals("abcd", wechatTpAuthJsConfigResp.getAppId());
            assertEquals("nonceStr", wechatTpAuthJsConfigResp.getNonceStr());
            assertEquals(1000, wechatTpAuthJsConfigResp.getTimestamp());
            assertEquals("signature", wechatTpAuthJsConfigResp.getSignature());

            val wechatCorpTokenResp = query.wechatCorpToken("instance");
            assertEquals("10000", wechatCorpTokenResp.getCorpId());
            assertEquals("instanceToken", wechatCorpTokenResp.getToken());

            val wechatCorpTpAuthTokenResp = query.wechatCorpTpAuthToken("instance", "xyz");
            assertEquals("10000", wechatCorpTpAuthTokenResp.getCorpId());
            assertEquals("xyz", wechatCorpTpAuthTokenResp.getSuiteId());
            assertEquals("instanceToken", wechatCorpTpAuthTokenResp.getToken());

            val toutiaoAppTokenResp = query.toutiaoAppToken("instance");
            assertEquals("2000", toutiaoAppTokenResp.getAppId());
            assertEquals("instanceToken", toutiaoAppTokenResp.getToken());

            val fengniaoAppTokenResp = query.fengniaoAppToken("instance", "xyz");
            assertEquals("3000", fengniaoAppTokenResp.getAppId());
            assertEquals("xyz", fengniaoAppTokenResp.getMerchantId());
            assertEquals("instanceToken", fengniaoAppTokenResp.getToken());

            val shansongAppTokenResp = query.shansongAppToken("instance", "xyz");
            assertEquals("4000", shansongAppTokenResp.getAppId());
            assertEquals("xyz", shansongAppTokenResp.getMerchantCode());
            assertEquals("instanceToken", shansongAppTokenResp.getToken());
        }
    }

    @EverythingIsNonNull
    @SneakyThrows
    public static void proxyInstanceConfig(ProxyWechatAppDemo proxyWechatApp,
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
                        case "/varys/proxy-wechat-app/instance/wechatApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("instanceWechatAppResp");

                        case "/varys/proxy-wechat-app/instance/wechatAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("instanceWechatAppParamResp");

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

                        case "/varys/proxy-fengniao-app/instance/xyz/fengniaoApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("instanceFengniaoAppResp");

                        case "/varys/proxy-fengniao-app/instance/xyz/fengniaoAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("instanceFengniaoAppParamResp");

                        case "/varys/proxy-shansong-app-developer/instance/xyz/shansongApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("instanceShansongAppResp");

                        case "/varys/proxy-shansong-app-developer/instance/xyz/shansongAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("instanceShansongAppParamResp");

                        case "/varys/proxy-shansong-app-merchant/instance/shansongApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("instanceShansongAppResp");

                        case "/varys/proxy-shansong-app-merchant/instance/shansongAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("instanceShansongAppParamResp");

                        case "/varys/proxy-shansong-app-file/instance/shansongApp":
                            assertEquals("b", request.getHeader("a"));
                            return new MockResponse().setBody("instanceShansongAppResp");

                        case "/varys/proxy-shansong-app-file/instance/shansongAppParam/testParam":
                            assertEquals(jsonOf("a", "b"), request.getBody().readUtf8());
                            return new MockResponse().setBody("instanceShansongAppParamResp");
                    }
                    return new MockResponse()
                            .setResponseCode(HttpStatus.NOT_FOUND.value())
                            .setBody(HttpStatus.NOT_FOUND.getReasonPhrase());
                }
            });
            mockWebServer.start(4236);

            val wechatAppResp = proxyWechatApp.wechatApp("instance", "b");
            assertEquals("instanceWechatAppResp", wechatAppResp);

            val wechatAppParamResp = proxyWechatApp.wechatAppParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceWechatAppParamResp", wechatAppParamResp);

            val wechatTpResp = proxyWechatTp.wechatTp("instance", "b");
            assertEquals("instanceWechatTpResp", wechatTpResp);

            val wechatTpParamResp = proxyWechatTp.wechatTpParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceWechatTpParamResp", wechatTpParamResp);

            val wechatTpAuthResp = proxyWechatTpAuth.wechatTpAuth("instance", "abcd", "b");
            assertEquals("instanceWechatTpAuthResp", wechatTpAuthResp);

            val wechatTpAuthParamResp = proxyWechatTpAuth.wechatTpAuthParam("instance", "abcd", "testParam", jsonOf("a", "b"));
            assertEquals("instanceWechatTpAuthParamResp", wechatTpAuthParamResp);

            val wechatCorpResp = proxyWechatCorp.wechatCorp("instance", "b");
            assertEquals("instanceWechatCorpResp", wechatCorpResp);

            val wechatCorpParamResp = proxyWechatCorp.wechatCorpParam("instance", "testParam", "b");
            assertEquals("instanceWechatCorpParamResp", wechatCorpParamResp);

            val fengniaoAppResp = proxyFengniaoApp.fengniaoApp("instance", "xyz", "b");
            assertEquals("instanceFengniaoAppResp", fengniaoAppResp);

            val fengniaoAppParamResp = proxyFengniaoApp.fengniaoAppParam("instance", "xyz", "testParam", jsonOf("a", "b"));
            assertEquals("instanceFengniaoAppParamResp", fengniaoAppParamResp);

            val shansongAppDeveloperResp = proxyShansongAppDeveloper.shansongApp("instance", "xyz", "b");
            assertEquals("instanceShansongAppResp", shansongAppDeveloperResp);

            val shansongAppDeveloperParamResp = proxyShansongAppDeveloper.shansongAppParam("instance", "xyz", "testParam", jsonOf("a", "b"));
            assertEquals("instanceShansongAppParamResp", shansongAppDeveloperParamResp);

            val shansongAppMerchantResp = proxyShansongAppMerchant.shansongApp("instance", "b");
            assertEquals("instanceShansongAppResp", shansongAppMerchantResp);

            val shansongAppMerchantParamResp = proxyShansongAppMerchant.shansongAppParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceShansongAppParamResp", shansongAppMerchantParamResp);

            val shansongAppFileResp = proxyShansongAppFile.shansongApp("instance", "b");
            assertEquals("instanceShansongAppResp", shansongAppFileResp);

            val shansongAppFileParamResp = proxyShansongAppFile.shansongAppParam("instance", "testParam", jsonOf("a", "b"));
            assertEquals("instanceShansongAppParamResp", shansongAppFileParamResp);
        }
    }
}

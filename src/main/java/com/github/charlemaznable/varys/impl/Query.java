package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.Parameter;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.core.net.ohclient.OhClient;
import com.github.charlemaznable.core.net.ohclient.annotation.ClientTimeout;
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

@OhClient
@Mapping(urlProvider = VarysQueryUrlProvider.class)
@ClientTimeout(
        callTimeoutProvider = VarysCallTimeoutProvider.class,
        connectTimeoutProvider = VarysConnectTimeoutProvider.class,
        readTimeoutProvider = VarysReadTimeoutProvider.class,
        writeTimeoutProvider = VarysWriteTimeoutProvider.class
)
public interface Query {

    @Mapping("/query-wechat-app-token/{codeName}")
    WechatAppTokenResp wechatAppToken(@PathVar("codeName") String codeName);

    @Mapping("/proxy-wechat-app-mp-login/{codeName}")
    WechatAppMpLoginResp wechatAppMpLogin(@PathVar("codeName") String codeName,
                                          @Parameter("js_code") String jsCode);

    @Mapping("/query-wechat-app-js-config/{codeName}")
    WechatJsConfigResp wechatAppJsConfig(@PathVar("codeName") String codeName,
                                         @Parameter("url") String url);

    @Mapping("/query-wechat-tp-token/{codeName}")
    WechatTpTokenResp wechatTpToken(@PathVar("codeName") String codeName);

    @Mapping("/query-wechat-tp-auth-token/{codeName}/{authorizerAppId}")
    WechatTpAuthTokenResp wechatTpAuthToken(@PathVar("codeName") String codeName,
                                            @PathVar("authorizerAppId") String authorizerAppId);

    @Mapping("/proxy-wechat-tp-auth-mp-login/{codeName}/{authorizerAppId}")
    WechatTpAuthMpLoginResp wechatTpAuthMpLogin(@PathVar("codeName") String codeName,
                                                @PathVar("authorizerAppId") String authorizerAppId,
                                                @Parameter("js_code") String jsCode);

    @Mapping("/query-wechat-tp-auth-js-config/{codeName}/{authorizerAppId}")
    WechatJsConfigResp wechatTpAuthJsConfig(@PathVar("codeName") String codeName,
                                            @PathVar("authorizerAppId") String authorizerAppId,
                                            @Parameter("url") String url);

    @Mapping("/query-wechat-corp-token/{codeName}")
    WechatCorpTokenResp wechatCorpToken(@PathVar("codeName") String codeName);

    @Mapping("/query-wechat-corp-tp-auth-token/{codeName}/{corpId}")
    WechatCorpTpAuthTokenResp wechatCorpTpAuthToken(@PathVar("codeName") String codeName,
                                                    @PathVar("corpId") String corpId);

    @Mapping("/query-toutiao-app-token/{codeName}")
    ToutiaoAppTokenResp toutiaoAppToken(@PathVar("codeName") String codeName);

    @Mapping("/query-fengniao-app-token/{codeName}/{merchantId}")
    FengniaoAppTokenResp fengniaoAppToken(@PathVar("codeName") String codeName,
                                          @PathVar("merchantId") String merchantId);

    @Mapping("/query-shansong-app-token/{codeName}/{merchantCode}")
    ShansongAppTokenResp shansongAppToken(@PathVar("codeName") String codeName,
                                          @PathVar("merchantCode") String merchantCode);
}

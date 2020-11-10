package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.Parameter;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.core.net.ohclient.OhClient;
import com.github.charlemaznable.core.net.ohclient.annotation.ClientTimeout;
import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;
import com.github.charlemaznable.varys.resp.WechatMpLoginResp;

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
    AppTokenResp appToken(@PathVar("codeName") String codeName);

    @Mapping("/query-wechat-app-authorizer-token/{codeName}/{authorizerAppId}")
    AppAuthorizerTokenResp appAuthorizerToken(@PathVar("codeName") String codeName,
                                              @PathVar("authorizerAppId") String authorizerAppId);

    @Mapping("/query-wechat-corp-token/{codeName}")
    CorpTokenResp corpToken(@PathVar("codeName") String codeName);

    @Mapping("/query-wechat-corp-authorizer-token/{codeName}/{corpId}")
    CorpAuthorizerTokenResp corpAuthorizerToken(@PathVar("codeName") String codeName,
                                                @PathVar("corpId") String corpId);

    @Mapping("/query-toutiao-app-token/{codeName}")
    AppTokenResp toutiaoAppToken(@PathVar("codeName") String codeName);

    @Mapping("/proxy-wechat-mp-login/{codeName}")
    WechatMpLoginResp wechatMpLogin(@PathVar("codeName") String codeName,
                                    @Parameter("js_code") String jsCode);
}

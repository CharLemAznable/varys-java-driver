package com.github.charlemaznable.varys.impl;

import com.github.charlemaznable.core.net.common.FixedPathVar;
import com.github.charlemaznable.core.net.common.Mapping;
import com.github.charlemaznable.core.net.common.PathVar;
import com.github.charlemaznable.core.net.ohclient.OhClient;
import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;

@OhClient
@Mapping(urlProvider = VarysQueryUrlProvider.class)
@FixedPathVar(name = "queryWechatAppToken", valueProvider = VarysPathProvider.class)
@FixedPathVar(name = "queryWechatAppAuthorizerToken", valueProvider = VarysPathProvider.class)
@FixedPathVar(name = "queryWechatCorpToken", valueProvider = VarysPathProvider.class)
@FixedPathVar(name = "queryWechatCorpAuthorizerToken", valueProvider = VarysPathProvider.class)
public interface Query {

    @Mapping("/{queryWechatAppToken}/{codeName}")
    AppTokenResp appToken(@PathVar("codeName") String codeName);

    @Mapping("/{queryWechatAppAuthorizerToken}/{codeName}/{authorizerAppId}")
    AppAuthorizerTokenResp appAuthorizerToken(@PathVar("codeName") String codeName,
                                              @PathVar("authorizerAppId") String authorizerAppId);

    @Mapping("/{queryWechatCorpToken}/{codeName}")
    CorpTokenResp corpToken(@PathVar("codeName") String codeName);

    @Mapping("/{queryWechatCorpAuthorizerToken}/{codeName}/{corpId}")
    CorpAuthorizerTokenResp corpAuthorizerToken(@PathVar("codeName") String codeName,
                                                @PathVar("corpId") String corpId);
}

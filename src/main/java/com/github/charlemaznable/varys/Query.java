package com.github.charlemaznable.varys;

import com.github.charlemaznable.core.net.ohclient.OhClient;
import com.github.charlemaznable.core.net.ohclient.OhMapping;
import com.github.charlemaznable.core.net.ohclient.param.OhPathVar;
import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;

@OhClient
@VarysMapping
public interface Query {

    @OhMapping("/{queryWechatAppToken}/{codeName}")
    AppTokenResp appToken(@OhPathVar("codeName") String codeName);

    @OhMapping("/{queryWechatAppAuthorizerToken}/{codeName}/{authorizerAppId}")
    AppAuthorizerTokenResp appAuthorizerToken(@OhPathVar("codeName") String codeName,
                                              @OhPathVar("authorizerAppId") String authorizerAppId);

    @OhMapping("/{queryWechatCorpToken}/{codeName}")
    CorpTokenResp corpToken(@OhPathVar("codeName") String codeName);

    @OhMapping("/{queryWechatCorpAuthorizerToken}/{codeName}/{corpId}")
    CorpAuthorizerTokenResp corpAuthorizerToken(@OhPathVar("codeName") String codeName,
                                                @OhPathVar("corpId") String corpId);
}

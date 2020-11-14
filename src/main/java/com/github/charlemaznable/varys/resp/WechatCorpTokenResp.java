package com.github.charlemaznable.varys.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class WechatCorpTokenResp {

    private String error;
    private String corpId;
    private String token;
}

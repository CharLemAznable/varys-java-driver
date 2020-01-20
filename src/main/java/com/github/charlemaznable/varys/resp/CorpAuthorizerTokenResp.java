package com.github.charlemaznable.varys.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class CorpAuthorizerTokenResp {

    private String token;
    private String error;
    private String suiteId;
    private String corpId;
}

package com.github.charlemaznable.varys.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorpTokenResp {

    private String token;
    private String error;
    private String corpId;
}

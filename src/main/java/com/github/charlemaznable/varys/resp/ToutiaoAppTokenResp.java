package com.github.charlemaznable.varys.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ToutiaoAppTokenResp {

    private String error;
    private String appId;
    private String token;
}

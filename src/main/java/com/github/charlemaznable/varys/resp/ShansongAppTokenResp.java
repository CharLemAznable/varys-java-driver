package com.github.charlemaznable.varys.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ShansongAppTokenResp {

    private String error;
    private String appId;
    private String merchantCode;
    private String token;
}

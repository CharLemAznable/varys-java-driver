package com.github.charlemaznable.varys.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppTokenResp {

    private String token;
    private String error;
    private String appId;
}

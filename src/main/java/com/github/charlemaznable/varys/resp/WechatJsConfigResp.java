package com.github.charlemaznable.varys.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class WechatJsConfigResp {

    private String appId;
    private String nonceStr;
    private long timestamp;
    private String signature;
}

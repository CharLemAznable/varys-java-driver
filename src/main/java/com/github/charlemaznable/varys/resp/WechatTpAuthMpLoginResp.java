package com.github.charlemaznable.varys.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class WechatTpAuthMpLoginResp {

    @JSONField(name = "openid")
    private String openId;
    @JSONField(name = "session_key")
    private String sessionKey;

    private int errcode;
    private String errmsg;
}

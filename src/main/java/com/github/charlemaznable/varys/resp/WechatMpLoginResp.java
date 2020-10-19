package com.github.charlemaznable.varys.resp;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WechatMpLoginResp {

    @JSONField(name = "openid")
    private String openId;
    @JSONField(name = "session_key")
    private String sessionKey;
    @JSONField(name = "unionid")
    private String unionId;

    private int errcode;
    private String errmsg;
}

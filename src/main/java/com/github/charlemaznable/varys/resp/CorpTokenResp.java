package com.github.charlemaznable.varys.resp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CorpTokenResp extends TokenResp {

    private String corpId;
}

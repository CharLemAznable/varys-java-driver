package com.github.charlemaznable.varys.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CorpTokenResp extends TokenResp {

    private String corpId;
}

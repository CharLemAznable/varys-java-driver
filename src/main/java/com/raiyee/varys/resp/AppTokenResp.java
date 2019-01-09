package com.raiyee.varys.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AppTokenResp extends TokenResp {

    private String appId;
}

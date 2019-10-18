package com.github.charlemaznable.varys.resp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TokenResp {

    private String token;
    private String error;
}

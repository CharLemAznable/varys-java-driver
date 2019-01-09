package com.raiyee.varys.api;

import com.raiyee.bone.net.HttpReq;
import com.raiyee.varys.Config;
import com.raiyee.varys.resp.AppAuthorizerTokenResp;
import com.raiyee.varys.resp.AppTokenResp;
import com.raiyee.varys.resp.CorpAuthorizerTokenResp;
import com.raiyee.varys.resp.CorpTokenResp;

import static com.raiyee.bone.codec.Json.unJson;
import static com.raiyee.bone.lang.Condition.nonBlank;

public class Query {

    private final Config config;
    private final String codeName;

    public Query(Config config) {
        this.config = config;
        this.codeName = config.getCodeName();
    }

    public Query(Config config, String codeName) {
        this.config = config;
        this.codeName = codeName;
    }

    public AppTokenResp appToken(String codeName) {
        String result = new HttpReq(config.getAddress() +
                "/query-wechat-app-token/" + codeName(codeName)).get();
        return unJson(result, AppTokenResp.class);
    }

    public AppTokenResp appToken() {
        return appToken(null);
    }

    public AppAuthorizerTokenResp appAuthorizerToken(String codeName, String authorizerAppId) {
        String result = new HttpReq(config.getAddress() +
                "/query-wechat-app-authorizer-token/" + codeName(codeName) + "/" + authorizerAppId).get();
        return unJson(result, AppAuthorizerTokenResp.class);
    }

    public AppAuthorizerTokenResp appAuthorizerToken(String authorizerAppId) {
        return appAuthorizerToken(null, authorizerAppId);
    }

    public CorpTokenResp corpToken(String codeName) {
        String result = new HttpReq(config.getAddress() +
                "/query-wechat-corp-token/" + codeName(codeName)).get();
        return unJson(result, CorpTokenResp.class);
    }

    public CorpTokenResp corpToken() {
        return corpToken(null);
    }

    public CorpAuthorizerTokenResp corpAuthorizerToken(String codeName, String corpId) {
        String result = new HttpReq(config.getAddress() +
                "/query-wechat-corp-authorizer-token/" + codeName(codeName) + "/" + corpId).get();
        return unJson(result, CorpAuthorizerTokenResp.class);
    }

    public CorpAuthorizerTokenResp corpAuthorizerToken(String corpId) {
        return corpAuthorizerToken(null, corpId);
    }

    private String codeName(String codeName) {
        String nonBlankCodeName = nonBlank(codeName, this.codeName);
        if (null == nonBlankCodeName) throw new RuntimeException("Missing parameter: CodeName");
        return nonBlankCodeName;
    }
}

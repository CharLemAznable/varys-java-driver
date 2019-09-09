package com.github.charlemaznable.varys.instanceConfig;

import com.github.charlemaznable.varys.mock.MockVarysServer;
import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;
import com.github.charlemaznable.varys.spring.VarysImport;
import lombok.val;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.github.charlemaznable.core.codec.Json.json;

@ComponentScan
@VarysImport
public class InstanceConfiguration {

    @PostConstruct
    public void postConstruct() {
        MockVarysServer.setUpMockServer();
        val instanceAppTokenResp = new AppTokenResp();
        instanceAppTokenResp.setAppId("1000");
        instanceAppTokenResp.setToken("instanceToken");
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/query-wechat-app-token/instance",
                json(instanceAppTokenResp));
        val instanceAppAuthorizerTokenResp = new AppAuthorizerTokenResp();
        instanceAppAuthorizerTokenResp.setAppId("1000");
        instanceAppAuthorizerTokenResp.setAuthorizerAppId("abcd");
        instanceAppAuthorizerTokenResp.setToken("instanceToken");
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/query-wechat-app-authorizer-token/instance/abcd",
                json(instanceAppAuthorizerTokenResp));
        val instanceCorpTokenResp = new CorpTokenResp();
        instanceCorpTokenResp.setCorpId("10000");
        instanceCorpTokenResp.setToken("instanceToken");
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/query-wechat-corp-token/instance",
                json(instanceCorpTokenResp));
        val instanceCorpAuthorizerTokenResp = new CorpAuthorizerTokenResp();
        instanceCorpAuthorizerTokenResp.setCorpId("10000");
        instanceCorpAuthorizerTokenResp.setSuiteId("xyz");
        instanceCorpAuthorizerTokenResp.setToken("instanceToken");
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/query-wechat-corp-authorizer-token/instance/xyz",
                json(instanceCorpAuthorizerTokenResp));
        val instanceWechatAppResp = "instanceWechatAppResp";
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/proxy-wechat-app/instance/wechatApp",
                instanceWechatAppResp);
        val instanceWechatAppParamResp = "instanceWechatAppParamResp";
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/proxy-wechat-app/instance/wechatAppParam/testParam",
                instanceWechatAppParamResp);
        val instanceWechatCorpResp = "instanceWechatCorpResp";
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/proxy-wechat-corp/instance/wechatCorp",
                instanceWechatCorpResp);
        val instanceWechatCorpParamResp = "instanceWechatCorpParamResp";
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/proxy-wechat-corp/instance/wechatCorpParam/testParam",
                instanceWechatCorpParamResp);
    }

    @PreDestroy
    public void preDestroy() {
        MockVarysServer.tearDownMockServer();
    }
}

package com.github.charlemaznable.varys.interfaceConfig;

import com.github.charlemaznable.core.miner.MinerScan;
import com.github.charlemaznable.varys.mock.MockVarysServer;
import com.github.charlemaznable.varys.resp.AppAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.AppTokenResp;
import com.github.charlemaznable.varys.resp.CorpAuthorizerTokenResp;
import com.github.charlemaznable.varys.resp.CorpTokenResp;
import com.github.charlemaznable.varys.spring.VarysImport;
import lombok.val;
import org.n3r.diamond.client.impl.MockDiamondServer;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.github.charlemaznable.core.codec.Json.json;

@ComponentScan
@VarysImport
@MinerScan
public class InterfaceConfiguration {

    @PostConstruct
    public void postConstruct() {
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("VARYS", "test",
                "address=http://127.0.0.1:4236/varys\n");

        MockVarysServer.setUpMockServer();
        val interfaceAppTokenResp = new AppTokenResp();
        interfaceAppTokenResp.setAppId("1000");
        interfaceAppTokenResp.setToken("interfaceToken");
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/query-wechat-app-token/interface",
                json(interfaceAppTokenResp));
        val interfaceAppAuthorizerTokenResp = new AppAuthorizerTokenResp();
        interfaceAppAuthorizerTokenResp.setAppId("1000");
        interfaceAppAuthorizerTokenResp.setAuthorizerAppId("abcd");
        interfaceAppAuthorizerTokenResp.setToken("interfaceToken");
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/query-wechat-app-authorizer-token/interface/abcd",
                json(interfaceAppAuthorizerTokenResp));
        val interfaceCorpTokenResp = new CorpTokenResp();
        interfaceCorpTokenResp.setCorpId("10000");
        interfaceCorpTokenResp.setToken("interfaceToken");
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/query-wechat-corp-token/interface",
                json(interfaceCorpTokenResp));
        val interfaceCorpAuthorizerTokenResp = new CorpAuthorizerTokenResp();
        interfaceCorpAuthorizerTokenResp.setCorpId("10000");
        interfaceCorpAuthorizerTokenResp.setSuiteId("xyz");
        interfaceCorpAuthorizerTokenResp.setToken("interfaceToken");
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/query-wechat-corp-authorizer-token/interface/xyz",
                json(interfaceCorpAuthorizerTokenResp));
        val interfaceWechatAppResp = "interfaceWechatAppResp";
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/proxy-wechat-app/interface/wechatApp",
                interfaceWechatAppResp);
        val interfaceWechatAppParamResp = "interfaceWechatAppParamResp";
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/proxy-wechat-app/interface/wechatAppParam/testParam",
                interfaceWechatAppParamResp);
        val interfaceWechatCorpResp = "interfaceWechatCorpResp";
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/proxy-wechat-corp/interface/wechatCorp",
                interfaceWechatCorpResp);
        val interfaceWechatCorpParamResp = "interfaceWechatCorpParamResp";
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/proxy-wechat-corp/interface/wechatCorpParam/testParam",
                interfaceWechatCorpParamResp);
    }

    @PreDestroy
    public void preDestroy() {
        MockVarysServer.tearDownMockServer();
        MockDiamondServer.tearDownMockServer();
    }
}

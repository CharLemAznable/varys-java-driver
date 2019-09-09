package com.github.charlemaznable.varys.defaultConfig;

import com.github.charlemaznable.core.miner.MinerFactory;
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
import static org.joor.Reflect.onClass;

@ComponentScan
@VarysImport
public class DefaultConfiguration {

    @PostConstruct
    public void postConstruct() {
        onClass(MinerFactory.class).field("minerCache").call("invalidateAll");
        MockDiamondServer.setUpMockServer();
        MockDiamondServer.setConfigInfo("VARYS", "default",
                "address=http://127.0.0.1:4236/varys\n");

        new MockVarysServer();
        MockVarysServer.setUpMockServer();
        val defaultAppTokenResp = new AppTokenResp();
        defaultAppTokenResp.setAppId("1000");
        defaultAppTokenResp.setToken("defaultToken");
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/query-wechat-app-token/default",
                json(defaultAppTokenResp));
        val defaultAppAuthorizerTokenResp = new AppAuthorizerTokenResp();
        defaultAppAuthorizerTokenResp.setAppId("1000");
        defaultAppAuthorizerTokenResp.setAuthorizerAppId("abcd");
        defaultAppAuthorizerTokenResp.setToken("defaultToken");
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/query-wechat-app-authorizer-token/default/abcd",
                json(defaultAppAuthorizerTokenResp));
        val defaultCorpTokenResp = new CorpTokenResp();
        defaultCorpTokenResp.setCorpId("10000");
        defaultCorpTokenResp.setToken("defaultToken");
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/query-wechat-corp-token/default",
                json(defaultCorpTokenResp));
        val defaultCorpAuthorizerTokenResp = new CorpAuthorizerTokenResp();
        defaultCorpAuthorizerTokenResp.setCorpId("10000");
        defaultCorpAuthorizerTokenResp.setSuiteId("xyz");
        defaultCorpAuthorizerTokenResp.setToken("defaultToken");
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/query-wechat-corp-authorizer-token/default/xyz",
                json(defaultCorpAuthorizerTokenResp));
        val defaultWechatAppResp = "defaultWechatAppResp";
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/proxy-wechat-app/default/wechatApp",
                defaultWechatAppResp);
        val defaultWechatAppParamResp = "defaultWechatAppParamResp";
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/proxy-wechat-app/default/wechatAppParam/testParam",
                defaultWechatAppParamResp);
        val defaultWechatCorpResp = "defaultWechatCorpResp";
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/proxy-wechat-corp/default/wechatCorp",
                defaultWechatCorpResp);
        val defaultWechatCorpParamResp = "defaultWechatCorpParamResp";
        MockVarysServer.setVarysResponse(
                "http://127.0.0.1:4236/varys/proxy-wechat-corp/default/wechatCorpParam/testParam",
                defaultWechatCorpParamResp);
    }

    @PreDestroy
    public void preDestroy() {
        MockVarysServer.tearDownMockServer();
        MockDiamondServer.tearDownMockServer();
    }
}

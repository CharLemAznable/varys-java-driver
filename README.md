#### varys-java-driver

Java client for [varys](https://github.com/CharLemAznable/varys)

##### Initialize:

``` java
// with codeName
Varys varys = new Varys(Config.builder().address("http://127.0.0.1:4236/varys").codeName("your_code_name").build());
// without codeName
Varys varys = new Varys(Config.builder().address("http://127.0.0.1:4236/varys").build());
```

##### Query API

``` java
// with codeName
Query query = varys.query("your_code_name");
// without codeName
Query query = varys.query();
```

##### Do Query

``` java
// with codeName
AppTokenResp appTokenResp = query.appToken("your_code_name");
// without codeName
AppTokenResp appTokenResp = query.appToken();
```

``` java
// with codeName
AppAuthorizerTokenResp appAuthorizerTokenResp = query.appAuthorizerToken("your_code_name", "authorizerAppId");
// without codeName
AppAuthorizerTokenResp appAuthorizerTokenResp = query.appAuthorizerToken("authorizerAppId");
```

``` java
// with codeName
CorpTokenResp corpTokenResp = query.corpToken("your_code_name");
// without codeName
CorpTokenResp corpTokenResp = query.corpToken();
```

``` java
// with codeName
CorpAuthorizerTokenResp corpAuthorizerTokenResp = query.corpAuthorizerToken("your_code_name", "corpId");
// without codeName
CorpAuthorizerTokenResp corpAuthorizerTokenResp = query.corpAuthorizerToken("corpId");
```

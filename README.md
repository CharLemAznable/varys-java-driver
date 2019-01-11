### varys-java-driver

[![Build Status](https://travis-ci.org/CharLemAznable/varys-java-driver.svg?branch=master)](https://travis-ci.org/CharLemAznable/varys-java-driver)
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)

Java client for [varys](https://github.com/CharLemAznable/varys)

##### Initialize:

```java
// with codeName
Varys varys = new Varys(Config.builder().address("http://127.0.0.1:4236/varys").codeName("your_code_name").build());
```
```java
// without codeName
Varys varys = new Varys(Config.builder().address("http://127.0.0.1:4236/varys").build());
```

##### Query API

```java
// with codeName
Query query = varys.query("your_code_name");
```
```java
// without codeName
Query query = varys.query();
```

##### Do Query

```java
// with codeName
AppTokenResp appTokenResp = query.appToken("your_code_name");
```
```java
// without codeName
AppTokenResp appTokenResp = query.appToken();
```

```java
// with codeName
AppAuthorizerTokenResp appAuthorizerTokenResp = query.appAuthorizerToken("your_code_name", "authorizerAppId");
```
```java
// without codeName
AppAuthorizerTokenResp appAuthorizerTokenResp = query.appAuthorizerToken("authorizerAppId");
```

```java
// with codeName
CorpTokenResp corpTokenResp = query.corpToken("your_code_name");
```
```java
// without codeName
CorpTokenResp corpTokenResp = query.corpToken();
```

```java
// with codeName
CorpAuthorizerTokenResp corpAuthorizerTokenResp = query.corpAuthorizerToken("your_code_name", "corpId");
```
```java
// without codeName
CorpAuthorizerTokenResp corpAuthorizerTokenResp = query.corpAuthorizerToken("corpId");
```

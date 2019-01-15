### varys-java-driver

[![Build Status](https://travis-ci.org/CharLemAznable/varys-java-driver.svg?branch=master)](https://travis-ci.org/CharLemAznable/varys-java-driver)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.charlemaznable/varys-java-driver/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.charlemaznable/varys-java-driver/)
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)

Java client for [varys](https://github.com/CharLemAznable/varys)

##### Maven Dependency

```xml
<dependency>
  <groupId>com.github.charlemaznable</groupId>
  <artifactId>varys-java-driver</artifactId>
  <version>0.0.1</version>
</dependency>
```

##### Initialize:

```java
// LoadingCache<Config, Varys>
Varys varys = Varys.instance(Config.builder().address("http://127.0.0.1:4236/varys").build());
```

##### Query API

```java
// PoolProxy
Query query = varys.query();
```

##### Do Query

```java
// LoadingCache<String, AppTokenResp>
AppTokenResp appTokenResp = query.appToken("your_code_name");
```

```java
// LoadingCache<Pair<String, String>, AppAuthorizerTokenResp>
AppAuthorizerTokenResp appAuthorizerTokenResp = query.appAuthorizerToken("your_code_name", "authorizerAppId");
```

```java
// LoadingCache<String, CorpTokenResp>
CorpTokenResp corpTokenResp = query.corpToken("your_code_name");
```

```java
// LoadingCache<Pair<String, String>, CorpAuthorizerTokenResp>
CorpAuthorizerTokenResp corpAuthorizerTokenResp = query.corpAuthorizerToken("your_code_name", "corpId");
```

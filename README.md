### varys-java-driver

[![Build Status](https://travis-ci.org/CharLemAznable/varys-java-driver.svg?branch=master)](https://travis-ci.org/CharLemAznable/varys-java-driver)
[![codecov](https://codecov.io/gh/CharLemAznable/varys-java-driver/branch/master/graph/badge.svg)](https://codecov.io/gh/CharLemAznable/varys-java-driver)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.charlemaznable/varys-java-driver/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.charlemaznable/varys-java-driver/)
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
![GitHub code size](https://img.shields.io/github/languages/code-size/CharLemAznable/varys-java-driver)

Java client for [varys](https://github.com/CharLemAznable/varys)

##### Maven Dependency

```xml
<dependency>
  <groupId>com.github.charlemaznable</groupId>
  <artifactId>varys-java-driver</artifactId>
  <version>0.7.0</version>
</dependency>
```

##### Configuration

1. 在`{USER_HOME}/.diamond-client/config-data`下新建目录`VARYS`
2. 在`VARYS`目录下建立文件`default.diamond`
```properties
address=http://127.0.0.1:4236/varys
```

##### Initialize:

```java
Varys varys = new Varys();
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

##### Proxy API

```java
// PoolProxy
Proxy proxy = varys.proxy();
```

##### Using Proxy

```java
String getResult = proxy.wechatApp("your_code_name", "proxy path").prop("xxx", "XXX").param("yyy", "YYY").get();
String postResult = proxy.wechatApp("your_code_name", "proxy path").prop("xxx", "XXX").param("yyy", "YYY").post();
```

```java
String getResult = proxy.wechatCorp("your_code_name", "proxy path").prop("xxx", "XXX").param("yyy", "YYY").get();
String postResult = proxy.wechatCorp("your_code_name", "proxy path").prop("xxx", "XXX").param("yyy", "YYY").post();
```

```http
公众号微信接口代理路径:
https://api.weixin.qq.com/cgi-bin/{proxy path}

企业微信接口代理路径
https://qyapi.weixin.qq.com/cgi-bin/{proxy path}
```

#### Run Test In IntelliJ IDEA

```
// 添加 VM options
-javaagent:$MAVEN_REPOSITORY$/org/jmockit/jmockit/1.48/jmockit-1.48.jar
```

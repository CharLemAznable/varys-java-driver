### varys-java-driver

[![Build](https://github.com/CharLemAznable/varys-java-driver/actions/workflows/build.yml/badge.svg)](https://github.com/CharLemAznable/varys-java-driver/actions/workflows/build.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.charlemaznable/varys-java-driver/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.charlemaznable/varys-java-driver/)
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
![GitHub code size](https://img.shields.io/github/languages/code-size/CharLemAznable/varys-java-driver)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_varys-java-driver&metric=alert_status)](https://sonarcloud.io/dashboard?id=CharLemAznable_varys-java-driver)

[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_varys-java-driver&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=CharLemAznable_varys-java-driver)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_varys-java-driver&metric=bugs)](https://sonarcloud.io/dashboard?id=CharLemAznable_varys-java-driver)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_varys-java-driver&metric=security_rating)](https://sonarcloud.io/dashboard?id=CharLemAznable_varys-java-driver)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_varys-java-driver&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=CharLemAznable_varys-java-driver)

[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_varys-java-driver&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=CharLemAznable_varys-java-driver)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_varys-java-driver&metric=sqale_index)](https://sonarcloud.io/dashboard?id=CharLemAznable_varys-java-driver)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_varys-java-driver&metric=code_smells)](https://sonarcloud.io/dashboard?id=CharLemAznable_varys-java-driver)

[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_varys-java-driver&metric=ncloc)](https://sonarcloud.io/dashboard?id=CharLemAznable_varys-java-driver)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_varys-java-driver&metric=coverage)](https://sonarcloud.io/dashboard?id=CharLemAznable_varys-java-driver)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=CharLemAznable_varys-java-driver&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=CharLemAznable_varys-java-driver)

Java client for [varys](https://github.com/CharLemAznable/varys)

##### Maven Dependency

```xml
<dependency>
  <groupId>com.github.charlemaznable</groupId>
  <artifactId>varys-java-driver</artifactId>
  <version>2022.0.6</version>
</dependency>
```

##### Maven Dependency SNAPSHOT

```xml
<dependency>
  <groupId>com.github.charlemaznable</groupId>
  <artifactId>varys-java-driver</artifactId>
  <version>2022.0.7-SNAPSHOT</version>
</dependency>
```

##### Configuration

使用Diamond: ```group:Varys dataId:default```
使用Apollo: ```namespace:Varys propertyName:default```

必填配置项: ```address=http://127.0.0.1:4236/varys```指定varys服务地址

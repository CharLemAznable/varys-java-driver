package com.github.charlemaznable.varystest.spring.instanceconfig;

import com.github.charlemaznable.varys.config.VarysConfig;
import org.springframework.stereotype.Component;

@Component
public class InstanceConfig implements VarysConfig {

    @Override
    public String address() {
        return "http://127.0.0.1:4236/varys";
    }

    @Override
    public String callTimeoutString() {
        return "0";
    }

    @Override
    public String connectTimeoutString() {
        return "10000";
    }

    @Override
    public String readTimeoutString() {
        return "10000";
    }

    @Override
    public String writeTimeoutString() {
        return "10000";
    }
}

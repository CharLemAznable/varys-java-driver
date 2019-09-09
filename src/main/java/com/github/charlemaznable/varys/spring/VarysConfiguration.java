package com.github.charlemaznable.varys.spring;

import com.github.charlemaznable.core.spring.ComplexBeanNameGenerator;
import com.github.charlemaznable.core.spring.ComplexImport;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(nameGenerator = ComplexBeanNameGenerator.class)
@ComplexImport
public class VarysConfiguration {
}

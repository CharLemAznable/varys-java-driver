package com.github.charlemaznable.varys.spring;

import com.github.charlemaznable.core.net.ohclient.OhScan;
import com.github.charlemaznable.core.spring.ComplexComponentScan;
import com.github.charlemaznable.core.spring.ComplexImport;
import com.github.charlemaznable.varys.impl.Query;

@ComplexImport
@ComplexComponentScan(basePackageClasses = Query.class)
@OhScan(basePackageClasses = Query.class)
public final class VarysComponents {
}

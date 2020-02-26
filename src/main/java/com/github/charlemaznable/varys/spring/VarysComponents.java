package com.github.charlemaznable.varys.spring;

import com.github.charlemaznable.core.net.ohclient.OhScan;
import com.github.charlemaznable.core.spring.ComplexComponentScan;
import com.github.charlemaznable.core.spring.ComplexImport;
import com.github.charlemaznable.varys.impl.VarysScanAnchor;

@ComplexImport
@ComplexComponentScan(basePackageClasses = VarysScanAnchor.class)
@OhScan(basePackageClasses = VarysScanAnchor.class)
public final class VarysComponents {
}

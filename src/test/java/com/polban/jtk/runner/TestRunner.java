package com.polban.jtk.runner;

import org.junit.platform.suite.api.*;
import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key   = GLUE_PROPERTY_NAME,
        value = "com.polban.jtk.stepdefinitions"
)
@ConfigurationParameter(
        key   = PLUGIN_PROPERTY_NAME,
        value = "pretty, html:target/reports/report.html"
)
public class TestRunner { }
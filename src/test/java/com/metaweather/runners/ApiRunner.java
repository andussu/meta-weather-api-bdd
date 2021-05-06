package com.metaweather.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/default-html-reports",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt",
                "pretty"},
        features = "src/test/resources/features/API_features",
        glue = "com/metaweather/steps/API_steps",
        strict = true,
        dryRun = false,
        tags = "@important"
)
public class ApiRunner {
}

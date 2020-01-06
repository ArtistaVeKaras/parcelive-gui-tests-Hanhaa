package net.hanhaa.dev.executor;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import org.junit.runner.RunWith;

@RunWith(CucumberRunnerTest.class)
@CucumberOptions(
        format = {"pretty", "html:target/createConnection-report",
                "json:target/testing-report.json", "junit:target/testing-report.xml"},
        snippets = SnippetType.CAMELCASE,
        features = "src/test/resources/features",
        glue = "net.hanhaa.dev.steps",
        tags = {"@Profile"}
)
public class CucumberExecutorTest {
}

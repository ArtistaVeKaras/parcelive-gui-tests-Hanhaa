package net.hanhaa.dev.steps;

import cucumber.api.java.en.When;
import net.hanhaa.dev.helpers.RestConnector;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class RestSteps {

    @Autowired
    RestConnector restConnector;

    @When("^POST request with the following params:$")
    public void postRequestWithTheFollowingParams(Map<String, String> values) {
        restConnector.postRequest(values);
    }

}

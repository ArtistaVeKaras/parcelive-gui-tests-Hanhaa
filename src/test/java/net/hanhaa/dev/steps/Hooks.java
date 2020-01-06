package net.hanhaa.dev.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.hanhaa.dev.listeners.EventHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Hooks extends StepsContext {

    @Autowired
    EventFiringWebDriver driver;

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
    EventHandler eventHandler = new EventHandler();

    @Before
    public void beforeScenario(Scenario scenario){
        driver.register(eventHandler);
        logger.info("<------------------------------Start of scenario "+scenario.getName().toUpperCase()+"------------------------------------------>");
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) baseAPI.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png"); //stick it in the report
            logger.info("Screenshot was taken because test failed! ");
        }
        driver.unregister(eventHandler);
        logger.info("<------------------------------End of scenario "+scenario.getName().toUpperCase()+"------------------------------------------>");
    }
}

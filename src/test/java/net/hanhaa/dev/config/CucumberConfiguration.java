package net.hanhaa.dev.config;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("net.hanhaa.dev")
public class CucumberConfiguration {

    @Bean(destroyMethod = "quit")
    @Scope("singleton")
    @Profile("local")
    public WebDriver driverLocal() {
        ChromeDriverManager.getInstance().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--start-maximized");

        WebDriver wrappedDriver = new ChromeDriver(chromeOptions);
        EventFiringWebDriver driver = new EventFiringWebDriver(wrappedDriver);

        return driver;
    }

    @Bean(destroyMethod = "quit")
    @Scope("singleton")
    @Profile("jenkins")
    public WebDriver driverJenkins() {
        ChromeDriverManager.getInstance().setup();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--window-size=2320,2080");
        chromeOptions.setHeadless(true);
        chromeOptions.addArguments("--disable-gpu");

        WebDriver wrappedDriver = new ChromeDriver(chromeOptions);
        EventFiringWebDriver driver = new EventFiringWebDriver(wrappedDriver);

        return driver;
    }

    @Bean
    public String testRunID() {
        return "123";
    }
}

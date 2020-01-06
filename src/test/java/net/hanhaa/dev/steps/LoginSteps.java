package net.hanhaa.dev.steps;

import cucumber.api.java.en.Given;
import net.hanhaa.dev.pages.admin.LoginPage;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.sql.SQLException;

public class LoginSteps {

    @Autowired
    EventFiringWebDriver driver;
    @Autowired
    LoginPage loginPage;
    @Value("${application.url}")
    String rootURL;

    @Given("^Access portal$")
    public void accessPortal() {
        driver.get(rootURL);
    }

    @Given("^Login with admin$")
    public void loginWithAdmin() throws SQLException {
        loginPage.loginWithAdmin();
    }
}

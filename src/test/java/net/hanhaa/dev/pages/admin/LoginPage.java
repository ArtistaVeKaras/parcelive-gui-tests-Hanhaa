package net.hanhaa.dev.pages.admin;

import net.hanhaa.dev.database.DatabaseOperations;
import net.hanhaa.dev.helpers.RestConnector;
import net.hanhaa.dev.pages.BasePage;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class LoginPage extends BasePage {

    @Autowired
    RestConnector restConnector;

    private DatabaseOperations db = new DatabaseOperations();

    private final String loginLogo = "//img[@src='images/login_logo.png']";
    private final String userNameFieldLocator = "//*[@id='username']";
    private final String passwordFieldLocator = "//*[@id='password']";
    private final String loginButtonLocator = "//*[@id='login_ok']";
    private final String accountDropdownLocator = "//li[@class='dropdown']/a[contains(text(), 'My Account')]";


    public void loginWith(String username) throws SQLException {
        waitForElementsToBeVisible(By.xpath(loginLogo), 5);
        $(userNameFieldLocator).sendKeys(username);
        $(passwordFieldLocator).sendKeys(db.selectPasswordByUsername(username));
        $(loginButtonLocator).click();
        waitUntilNoLoader();
        waitForElementsToBeVisible(By.xpath(accountDropdownLocator), 5);
    }

    public void loginWithAdmin() throws SQLException {
        deleteAllCookies();
        driver.get("https://dev.hanhaa.net/auth/login");
        loginWith("admin");
//        restConnector.postRequestToSetAdminCookies();
    }
}

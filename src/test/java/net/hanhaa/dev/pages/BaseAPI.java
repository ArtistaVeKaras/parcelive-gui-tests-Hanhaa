package net.hanhaa.dev.pages;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;


@Component
public class BaseAPI {

    private static final Logger logger = LoggerFactory.getLogger(BaseAPI.class);

    @Autowired
    protected WebDriver driver;

    private FluentWait<WebDriver> waitFor(int timeoutSeconds) {
        return new FluentWait<>(driver)

                .withTimeout(timeoutSeconds, SECONDS)

                .pollingEvery(100, MILLISECONDS)

                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void waitForElementToBeVisible(By element, int timeoutSeconds) {
        logger.debug("Wait for element: " + element + " to be visible!");
        waitFor(timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(element));
        logger.debug("Element: " + element+ " is visible!");
    }

    public void waitForElementNotToBeVisible(By element, int timoutSeconds){
        logger.debug("Wait for element: " + element + " not to be visible!");
        waitFor(timoutSeconds).until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(element)));
        logger.debug("Element: " + element + " is not visible!");
    }

    public void waitForAlertToBeVisible(int timeoutSeconds) {
        logger.debug("Wait for alert to be visible!");
        waitFor(timeoutSeconds).until(ExpectedConditions.alertIsPresent());
        logger.debug("Alert is visible!");
    }

    public void waitForElementToBeInvisible(By element, int timeoutSeconds) {
        logger.debug("Element: " + element + " is not invisible!!!");
        logger.debug("Waiting for element: " + element + " to be invisible!");
        waitFor(timeoutSeconds).until(ExpectedConditions.invisibilityOfElementLocated(element));
        logger.debug("Element: " + element + " is now invisible!");
    }

    public void waitForElementsToBeVisible(By element, int timeoutSeconds) {
        logger.debug("Waiting for elements: " + element + " to be visible!");
        waitFor(timeoutSeconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
        logger.debug("Element: " + element+ " is visible!");
    }


    public void waitForFrameToBePresent(By element, int timeoutSeconds) {
        logger.debug("Wait for frame to be present!");
        waitFor(timeoutSeconds).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        logger.debug("Frame is present!");
    }

    public void waitForElementToBeClickable(By element, int timeoutSeconds) {
        logger.debug("Waiting for element: " + element + " to be clickable!");
        waitFor(timeoutSeconds).until(ExpectedConditions.elementToBeClickable(element));
        logger.debug("Element: " + element + " is clickable!");
    }

    public boolean waitForElementToBeVisibleAndTextToBe(By element, String text, int timeoutSeconds) {
        logger.debug("Waiting for element: " + element + " to be visible with text: '" + text + "'");
        return waitFor(timeoutSeconds).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(element),
                ExpectedConditions.textToBe(element, text)));
    }

    public boolean waitForElementToBeVisibleAndTextNotToBe(By element, String text, int timeoutSeconds) {
        logger.debug("Waiting for element: " + element + " to be visible with text NOT: '" + text + "'");
        return waitFor(timeoutSeconds).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(element),
                ExpectedConditions.not(ExpectedConditions.textToBe(element, text))));
    }

    public void waitForUrlToBe(String url, int timeoutSeconds) {
        logger.debug("Waiting for url to be: '" + url + "'");
        waitFor(timeoutSeconds).until(ExpectedConditions.urlToBe(url));
        logger.debug("Url is " + "'" + url + "'");
    }

    public void waitForUrlToContain(String url, int timeoutSeconds) {
        logger.debug("Waiting for url contains text: '" + url +"'");
        waitFor(timeoutSeconds).until(ExpectedConditions.urlContains(url));
        logger.debug("Url contains " + "'" + url + "'");
    }

    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    public <V> V assertThat(Function<? super WebDriver, V> condition) {
        return (new WebDriverWait(getDriver(), 5).until(condition));
    }

    public WebElement $(By locator) {
        return assertThat(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement $(String xpath) { return $(By.xpath(xpath)); }

    public List<WebElement> $$(By locator) {
        return assertThat(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public List<WebElement> $$(String xpath) { return $$(By.xpath(xpath)); }

    public List<WebElement> getNotStale(By selector) throws InterruptedException {
        int maxRetries = 5;
        int attempt = 0;

        while (attempt++ < maxRetries) {
            try {
                $$(selector).size();
                return $$(selector);
            } catch (StaleElementReferenceException e) {
                Thread.sleep(100);
            }
        }
        throw new StaleElementReferenceException("Stale element in table");
    }

    public void clickWithJS(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var evt = document.createEvent('MouseEvents');" +
                "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
                + "arguments[0].dispatchEvent(evt);", $(locator));
    }
}


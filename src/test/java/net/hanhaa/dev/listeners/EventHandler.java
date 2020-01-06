package net.hanhaa.dev.listeners;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EventHandler implements WebDriverEventListener {

    private static final Logger logger = LoggerFactory.getLogger(EventHandler.class);
    private WebElement lastElement;
    private String originalValue;

    @Override
    public void beforeAlertAccept(WebDriver driver) {
        logger.debug("Before alert accept");
    }

    @Override
    public void afterAlertAccept(WebDriver driver) {
        logger.debug("After alert accept");
    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {
        logger.debug("After alert dismiss");
    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {
        logger.debug("Before alert dismiss");
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        logger.debug("Before navigating to : " + url + ", my url was: "
                + driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        logger.debug("After navigating to: " + url + ", my url is: "
                + driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        logger.debug("Before Navigating Back. I was at "
                + driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        logger.debug("After navigating back. I'm at "
                + driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        logger.debug("Before navigating norward. I was at "
                + driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        logger.debug("After navigating forward. I'm at "
                + driver.getCurrentUrl());
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        logger.debug("Before refresh. I was at "
                + driver.getCurrentUrl());
    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        logger.debug("After refresh. I'm at "
                + driver.getCurrentUrl());
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        logger.debug("Trying to find: " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        logger.debug("Found: " + by.toString() + ".");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        logger.debug("Trying to click: '" + element + "'");

        //Highlight Elements before clicking
        for (int i = 0; i < 1; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "color: black; border: 3px solid black;");
        }

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        logger.debug("Clicked Element '" + element + "'");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] charSequences) {
        lastElement = element;
        originalValue = element.getText();

        // What if the element is not visible anymore?
        if (originalValue.isEmpty()) {
            originalValue = element.getAttribute("value");
        }
        logger.debug("Before Change Value of element + " + element + " Origin Value was = "
                + "'" + originalValue + "'");
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] charSequences) {
        lastElement = element;
        String changedValue = "";
        try {
            changedValue = element.getText();
        } catch (StaleElementReferenceException e) {
            logger.debug("Could not log change of element, because of a stale"
                    + " element reference exception.");
            return;
        }

        if (changedValue.isEmpty()) {
            changedValue = element.getAttribute("value");
        }

        logger.debug("Changing value in element found " + element
                + " from '" + originalValue + "' to '" + changedValue + "'");
    }

    @Override
    public void beforeScript(String s, WebDriver driver) {
        logger.debug("Before script, " + s);
    }

    @Override
    public void afterScript(String s, WebDriver driver) {
        logger.debug("After script, " + s);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        // TODO Auto-generated method stub
    }
}

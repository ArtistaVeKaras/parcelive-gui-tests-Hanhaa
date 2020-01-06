package net.hanhaa.dev.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Component
public class BasePage extends BaseAPI {

    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    public static Set<Cookie> cookies;
    private final String tableLoader = ".bjui-maskBackground.bjui-ajax-mask";
    private final String simListButtonLocator = "//span[contains(.,'Sim List')]";
    private final String deviceListButtonLocator = "//span[contains(.,'Device List')]";
    private final String distributorListButtonLocator = "//span[contains(.,'Distributor List')]";
    private final String customerOrderListButtonLocator = "//span[contains(.,'Customer Order List')]";
    private final String shipmentListButtonLocator = "//span[contains(.,'Shipment List')]";
    private final String userListButtonLocator = "//a[@id='bjui-hnav-tree1_2_a']";
    private final String customerListButtonLocator = "//span[contains(.,'Customer List')]";
    private final String billingListButtonLocator = "//span[contains(.,'Billing List')]";
    private final String distributorContractList = "//span[contains(.,'Distributor Contract List')]";
    private final String addressList = "//span[contains(.,'Address List')]";
    private final String tidList = "//span[contains(.,'TID List')]";
    protected final String portalAlert = "//div[@id='bjui-alertMsgBox']";
    protected final By successAlertOkButton = By.cssSelector("#bjui-alertMsgBox .btn-default");
    protected final By confirmOkAlertButton = By.cssSelector("#bjui-alertMsgBox .btn-green");
    protected final String accountDropdownLocator = "//li[@class='dropdown']/a[contains(text(), 'My Account')]";
    protected final String logoutButtonLocator = "//a[contains(@href,'/logout')]";
    protected final String welcomeFieldLocator = "//nav[@id='bjui-navbar-collapse']/ul/li[1]//span";

    public void waitUntilNoLoader() {
        WebDriverWait wait = new WebDriverWait(driver, 5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(tableLoader)));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(tableLoader)));
    }

    public void selectTheFollowingButtonFromNavMenu(String navMenuButton) throws Exception {
        switch (navMenuButton) {
            case "Sim List":
                $(simListButtonLocator).click();
                break;
            case "Device List":
                $(deviceListButtonLocator).click();
                break;
            case "Distributor List":
                $(distributorListButtonLocator).click();
                break;
            case "User List":
                $(userListButtonLocator).click();
                break;
            case "Customer List":
                $(customerListButtonLocator).click();
                break;
            case "Shipment List":
                $(shipmentListButtonLocator).click();
                break;
            case "Customer Order List":
                $(customerOrderListButtonLocator).click();
                break;
            case "Billing List":
                $(billingListButtonLocator).click();
                break;
            case "Distributor Contract List":
                $(distributorContractList).click();
                break;
            case "Address List":
                $(addressList).click();
                break;
            case "TID List":
                $(tidList).click();
                break;
            default:
                logger.info("No such table in portal was found");
                throw new Exception();
        }
        waitUntilNoLoader();
    }

    public void clickOkButtonAtSuccessAlert() {
        $(successAlertOkButton).click();
        waitUntilNoLoader();
    }

    public void  logoutFromApp() {
//        $(accountDropdownLocator).click();
//        $(By.cssSelector(".bjui-navbar-right a.dropdown-toggle")).click();
//        $("//a[@class='dropdown-toggle' and contains(text(),'My Account')]").click();
//        $("//span[@class=\"caret\"]").click();
        clickWithJS(By.xpath("//span[@class=\"caret\"]"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        waitForElementToBeVisible(By.xpath("//ul[@class='dropdown-menu']"), 2);
        $("//a[contains(@href,'/logout')]").click();
//        $(By.cssSelector(".bjui-navbar-right a.red")).click();
//        waitForElementToBeClickable(By.xpath(logoutButtonLocator), 2);
//        $(logoutButtonLocator).click();
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}

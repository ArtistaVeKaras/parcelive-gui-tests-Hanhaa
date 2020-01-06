package net.hanhaa.dev.pages.admin;

import net.hanhaa.dev.database.DatabaseOperations;
import net.hanhaa.dev.helpers.StepsHelper;
import net.hanhaa.dev.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

@Component
public class DeviceListPage extends BasePage {

    @Autowired
    StepsHelper stepsHelper;
    private DatabaseOperations db = new DatabaseOperations();

    private final String addProductionOrderButton = "//a[@data-title='Add Production Order']";
    private final String createDevicesButton = "//a[@data-title='Binding Device']";
    private final String factoryQuickCircuitsButton = "//div[@class='dropdown-menu open']/ul/li[.='0034-Quick Circuits']/a";
    private final String factorySelectButton = "//button[@title='Please Select']";
    private final String quantityInput = "//input[@id='qty']";
    private final String deviceNameInput = "//input[@id='productName']";
    private final String modelNumberCharInput = "//input[@id='pnCh']";
    private final String modelNumberDigitInput = "//input[@id='pnNo']";
    private final String modelNumberSecondDigitInput = "//input[@id='pnV']";
    private final String filterByModelNumberButtonLocator = "//*[@id='modelNumber']";
    private final String serialNumberFilterInput = "//*[@id='serialNumber']";
    private final String firstSNTableRow = "//div[@style='display: block;']//tbody//td[1]";
    private final String firstModelNumberTableRow = "//div[@style='display: block;']//tbody//td[3]";
    private final String firstProductNameTableRow = "//div[@style='display: block;']//tbody//td[4]";
    private final String firstIMEITableRow = "//div[@style='display: block;']//tbody//td[6]";
    private final String firstStatusTableRow = "//div[@style='display: block;']//tbody//td[8]";
    private final String firstTargetCompanyTableRow = "//div[@style='display: block;']//tbody//td[9]";
    private final String deviceStatusDropboxButton = "//*[@id='deviceEditVO']//button[@data-id='status']";
//    private final String deviceStatusOpenedDropbox = "/html/body/div[10]/div";
    private final String deviceStatusOpenedDropbox = "//div[@class='btn-group bootstrap-select show-tick open']//li[@class='selected']";
    private final String deviceDropboxReturnedStatus = "//div[@class='btn-group bootstrap-select show-tick open']//a/span[contains(.,'RETURNED')]";
    private final String deviceSelectCompanyDropboxButton = "//*[@id='deviceEditVO']//button[@data-id='companyId']";
    private final String selectFileCreateDevicesButton = "//div[@id='file']/input";
    private final String selectCompanySearchFieldInput = "//div[@class='btn-group bootstrap-select show-tick dropup open']//input";

    public void filterBySerialNumber(String deviceSN) {
        $(serialNumberFilterInput).sendKeys(deviceSN, Keys.ENTER);
        waitUntilNoLoader();
        Assert.assertThat($(firstSNTableRow).getText(), equalTo(deviceSN));
    }

    public void changeDeviceStateToReturned(String deviceSN) throws SQLException {
        Long deviceDatabaseId = db.getDeviceIdBySerialNumber(deviceSN);
        String deviceEditButtonLocator = "//a[contains(@href,'deviceEdit?id=" + deviceDatabaseId + "')]";
        $(deviceEditButtonLocator).click();
        waitUntilNoLoader();
        $(deviceStatusDropboxButton).click();
        waitForElementToBeVisible(By.xpath(deviceStatusOpenedDropbox), 2);
        $(deviceDropboxReturnedStatus).click();
        $(deviceSelectCompanyDropboxButton).click();
        //TODO how to select fulfillment center?
        $(selectCompanySearchFieldInput).sendKeys("ID Commerce + Logistics");
        $(By.xpath("//a/span[contains(.,'ID Commerce + Logistics')]")).click();
        stepsHelper.clickSaveButton();
        clickOkButtonAtSuccessAlert();
    }

    public void verifyDeviceReturnedStatus(String deviceSN) {
        Assert.assertThat($(firstSNTableRow).getText(), equalTo(deviceSN));
        Assert.assertThat($(firstStatusTableRow).getText(), equalTo("RETURNED"));
    }

    public void verifyDeviceWithValues(List<String> values) {
        if (values.size() == 3) {
            Assert.assertThat($(firstSNTableRow).getText(), equalTo(values.get(0)));
            Assert.assertThat($(firstStatusTableRow).getText(), equalTo(values.get(1)));
            Assert.assertThat($(firstTargetCompanyTableRow).getText(), equalTo(values.get(2)));
        } else{
            Assert.assertThat($(firstSNTableRow).getText(), equalTo(values.get(0)));
            Assert.assertThat($(firstModelNumberTableRow).getText(), equalTo(values.get(1)));
            Assert.assertThat($(firstProductNameTableRow).getText(), equalTo(values.get(2)));
            Assert.assertThat($(firstIMEITableRow).getText(), equalTo(values.get(3)));
            Assert.assertThat($(firstStatusTableRow).getText(), equalTo(values.get(4)));
            Assert.assertThat($(firstTargetCompanyTableRow).getText(), equalTo(values.get(5)));
        }
    }

    public void addProductionOrderClick() {
        $(addProductionOrderButton).click();
        waitUntilNoLoader();
    }

    public void fillProductionOrderData(Map<String, String> values) {
        $(quantityInput).sendKeys(values.get("Quantity"));
        $(deviceNameInput).sendKeys(values.get("Device Name"));
        String[] modelNumberSplit = values.get("Model Number").split("-");
        $(modelNumberCharInput).sendKeys(modelNumberSplit[0]);
        $(modelNumberDigitInput).sendKeys(modelNumberSplit[1]);
        $(modelNumberSecondDigitInput).sendKeys(modelNumberSplit[2]);
        $(factorySelectButton).click();
        $(factoryQuickCircuitsButton).click();
    }

    public void createDevicesClick() {
        $(createDevicesButton).click();
        waitUntilNoLoader();
    }

    public void uploadCreateDeviceExcelFile() {
        Path resourceDirectory = Paths.get("src/test/resources/createDevices.xlsx");
        String filePath = resourceDirectory.toAbsolutePath().toString();
        driver.findElement(By.xpath(selectFileCreateDevicesButton)).sendKeys(filePath);
    }
}

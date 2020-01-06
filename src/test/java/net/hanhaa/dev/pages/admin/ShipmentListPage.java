package net.hanhaa.dev.pages.admin;

import net.hanhaa.dev.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

@Component
public class ShipmentListPage extends BasePage {

    private final String importShipmentButton = "//*[@data-title='Process Shipment']";
    private final String confirmShipmentButtonLocator = "//*[@data-title='Confirm Shipment']";
    private final String shippingDateFieldLocator = "//*[@name='shippingDate']";
    private final String receivedDateFieldLocator = "//*[@name='receiptDate']";
    private final String shipmentNumberFieldLocator = "//*[@name='shipmentNumber']";
    private final String generateTIDButtonLocator = "//*[@id='generateTid']";
    private final String updateButtonLocator = "//*[@id='save']";
    private final String selectFilesButton = "//input[contains(@class,'bjui-upload-select-file')]";
    private final String selectFilesButton1 = "//*[@id=\"file\"]";
    private final String firstChannelPartnerField = "//div[@style='display: block;']//tbody/tr[1]/td[4]";
    private final String firstCustomerField = "//div[@style='display: block;']//tbody/tr[1]/td[5]";
    private final String firstProfileLabelField = "//div[@style='display: block;']//tbody/tr[1]/td[6]";
    private final String firstDeviceQuantityField = "//div[@style='display: block;']//tbody/tr[1]/td[9]";
    private final String firstDeviceShippingDateField = "//div[@style='display: block;']//tbody/tr[1]/td[10]";
    private final String firstStateField = "//div[@style='display: block;']//tbody/tr[1]/td[13]";


    public void verifyNewShipmentWasCreated(List<String> values) {
        Assert.assertThat($(firstChannelPartnerField).getText(), equalTo(values.get(0)));
        Assert.assertThat($(firstCustomerField).getText(), equalTo(values.get(1)));
        Assert.assertThat($(firstProfileLabelField).getText(), equalTo(values.get(2)));
        Assert.assertThat($(firstDeviceQuantityField).getText(), equalTo(values.get(3)));
        Assert.assertThat($(firstStateField).getText(), equalTo(values.get(4)));
    }
    
    public void importShipmentButtonClick() {
        $(importShipmentButton).click();
        waitUntilNoLoader();
    }

    public void fillShippingDateAsToday() {
       $(shippingDateFieldLocator).sendKeys(getCurrentDate());
    }

    public void uploadExcelFileWithDevice() {
        Path resourceDirectory = Paths.get("src/test/resources/coShipment.xlsx");
        String filePath = resourceDirectory.toAbsolutePath().toString();
        driver.findElement(By.xpath(selectFilesButton)).sendKeys(filePath);
    }

    public void verifyShipmentWithValues(List<String> values) {
        Assert.assertThat($(firstChannelPartnerField).getText(), equalTo(values.get(0)));
        Assert.assertThat($(firstCustomerField).getText(), equalTo(values.get(1)));
        Assert.assertThat($(firstStateField).getText(), equalTo(values.get(2)));
        Assert.assertThat($(firstDeviceShippingDateField).getText(), equalTo(getCurrentDate()));
    }
}

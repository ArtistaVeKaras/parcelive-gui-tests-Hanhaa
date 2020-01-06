package net.hanhaa.dev.helpers;

import net.hanhaa.dev.pages.BasePage;
import org.springframework.stereotype.Component;

@Component
public class StepsHelper extends BasePage {

    private final String tableHeadersLocator = "//table[@class='table table-bordered']";
    private final String saveButtonLocator = "//button[contains(.,' Save')]";


    public String[] getTableHeaders() {
        return $(tableHeadersLocator).getText().split("\\s+");
    }

    public void clickSaveButton() {
        $(saveButtonLocator).click();
    }
}

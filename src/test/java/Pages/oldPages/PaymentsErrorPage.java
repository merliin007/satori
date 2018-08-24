package pages.oldPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PaymentsErrorPage {

    @FindBy(how = How.ID, using = "__tab_ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpErrors")
    private WebElement errorTab;

    WebDriver _driver;

    public PaymentsErrorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    public WebElement getErrorTab(){
        return errorTab;
    }
}

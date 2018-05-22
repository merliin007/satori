package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddPaymentPage {
    private WebDriver _driver;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_cmdAdd")
    private WebElement btnAdd;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_productSelector_txtAltaNumber")
    private WebElement txtAltaNumber;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_ddlPaymentMethod")
    private WebElement drpPaymentMethod;

    @FindBy(how = How.ID, using = "braintree-hosted-field-number")
    private WebElement ccNumberIframe;

    @FindBy(how = How.ID, using = "braintree-hosted-field-expirationMonth")
    private WebElement ccExpirationMonthIframe;

    @FindBy(how = How.ID, using = "braintree-hosted-field-expirationYear")
    private WebElement ccExpirationYearIframe;

    @FindBy(how = How.ID, using = "braintree-hosted-field-cvv")
    private WebElement ccCVVIframe;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_btnSubmit")
    private WebElement btnPaymentSubmit;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_btnBack")
    private WebElement btnCancel;

    public AddPaymentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    public WebElement getBtnAdd() {
        return btnAdd;
    }

    public void setTxtAltaNumber(String text) {
        txtAltaNumber.clear();
        txtAltaNumber.sendKeys(text);

    }

    public void setDrpPaymentMethod(String option) {
        Select sel = new Select(drpPaymentMethod);
        switch (option.toLowerCase()) {
            case "credit card":
                sel.selectByVisibleText("Credit Card");
                break;
            default:
                System.out.println("Incorrect option");
        }
    }

    public void setCreditCardInformationAndSubmit(String ccNumber, String name, String expirationMonth, String expirationYear, String cvv) throws Exception{
        _driver.switchTo().frame(ccNumberIframe);
        _driver.findElement(By.id("credit-card-number")).sendKeys(ccNumber);
        _driver.switchTo().defaultContent();

        _driver.findElement(By.id("cardholderName")).sendKeys(name);

        _driver.switchTo().frame(ccExpirationMonthIframe);
        WebElement element = _driver.findElement(By.id("expiration-month"));
        new Select(element).selectByVisibleText(expirationMonth);
        _driver.switchTo().defaultContent();

        _driver.switchTo().frame(ccExpirationYearIframe);
        element = _driver.findElement(By.id("expiration-year"));
        new Select(element).selectByVisibleText(expirationYear);
        _driver.switchTo().defaultContent();

        _driver.switchTo().frame(ccCVVIframe);
        _driver.findElement(By.id("cvv")).sendKeys(cvv);
        _driver.switchTo().defaultContent();

        btnPaymentSubmit.click();
        //btnCancel.click();


    }

}

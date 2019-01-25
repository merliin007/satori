/*
 * Created by Miguel Angel Aguilar Cuevas
 * 16/01/2019 at 10:58 AM
 */
package pages.memberPortal.payments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utility.payment.CreditCardInfo;

public class PendingPaymentsPage {

    private WebDriver _driver;

    public PendingPaymentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }


    @FindBy(how = How.ID, using = "braintree-hosted-field-number")
    private WebElement ccNumberIframe;

    @FindBy(how = How.ID, using = "braintree-hosted-field-expirationMonth")
    private WebElement ccExpirationMonthIframe;

    @FindBy(how = How.ID, using = "braintree-hosted-field-expirationYear")
    private WebElement ccExpirationYearIframe;

    @FindBy(how = How.ID, using = "braintree-hosted-field-cvv")
    private WebElement ccCVVIframe;

    @FindBy(how = How.NAME, using = "ctl00$ctl00$CPHolder$CPHolder$btnSubmit")
    private WebElement btnPaymentSubmit;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_btnBack")
    private WebElement btnCancel;

    @FindBy(how= How.ID, using="credit-card-number")
    private WebElement txtCCNumber;
    @FindBy(how= How.ID, using="cardholderName")
    private WebElement txtHolderName;
    @FindBy(how= How.ID, using="expiration-month")
    private WebElement drpExpMonth;
    @FindBy(how= How.ID, using="expiration-year")
    private WebElement drpExpYear;
    @FindBy(how= How.ID, using="cvv")
    private WebElement txtCvv;

    public void setCreditCardInformationAndSubmit(CreditCardInfo cardInfo) throws Exception {
        _driver.switchTo().frame(ccNumberIframe);
        txtCCNumber.sendKeys(cardInfo.getCcNumber());

        _driver.switchTo().defaultContent();
        txtHolderName.sendKeys(cardInfo.getCcHolder());

        _driver.switchTo().frame(ccExpirationMonthIframe);
        new Select(drpExpMonth).selectByVisibleText(cardInfo.getCcMonth());
        _driver.switchTo().defaultContent();

        _driver.switchTo().frame(ccExpirationYearIframe);
        new Select(drpExpYear).selectByVisibleText(cardInfo.getCcYear());
        _driver.switchTo().defaultContent();

        _driver.switchTo().frame(ccCVVIframe);
        txtCvv.sendKeys(cardInfo.getCcCVV());
        _driver.switchTo().defaultContent();

        btnPaymentSubmit.click();
    }

}

package pages.DefunctOldPages;

import base.CustomExceptions;
import utility.payment.BankAccountInfo;
import utility.payment.CheckInfo;
import utility.payment.CreditCardInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddPaymentPage {
    private WebDriver _driver;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_txtPaymentDateValue")
    private WebElement txtDate;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_txtPaymentTimeValue")
    private WebElement txtTime;

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

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_btnSubmitNoCC")
    private WebElement btnSubmitPaymentNoCC;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_btnBack")
    private WebElement btnCancel;

    @FindBy(how = How.ID, using = "accounttype")
    private WebElement drpAccountType;

    @FindBy(how = How.ID, using = "routingnumber")
    private WebElement txtRoutingNumber;

    @FindBy(how = How.ID, using = "accountnumber")
    private WebElement txtAccountNumber;

    @FindBy(how = How.ID, using = "txtFirstname")
    private WebElement txtFirstName;

    @FindBy(how = How.ID, using = "txtLastname")
    private WebElement txtLastName;

    @FindBy(how = How.ID, using = "billing-street-address")
    private WebElement txtStreet;

    @FindBy(how = How.ID, using = "billing-locality")
    private WebElement txtCity;

    @FindBy(how = How.ID, using = "billing-region")
    private WebElement drpState;

    @FindBy(how = How.ID, using = "billing-postal-code")
    private WebElement txtZip;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_productSelector_cbIsUpgrade")
    private WebElement chkUpgrade;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_paymentCheck_txtCheckNumber")
    private WebElement txtCheckNumber;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_paymentCheck_txtAccountHolderName")
    private WebElement txtCheckAccountHolder;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_gvPurchaseItems")
    private WebElement tblPurchases;

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

    public void setDrpPaymentMethod(String option) throws CustomExceptions {
        Select sel = new Select(drpPaymentMethod);
        switch (option.toLowerCase()) {
            case "credit card":
                sel.selectByVisibleText("Credit Card");
                break;
            case "bank account":
                sel.selectByVisibleText("Bank Account");
                break;
            case "office credit":
                sel.selectByVisibleText("Office Credit");
                break;
            case "check":
                sel.selectByVisibleText("Check");
                break;
            case "cash":
                sel.selectByVisibleText("Cash");
                break;
            default:
                throw new CustomExceptions(">>>> Payment option not found!!! <<<<");
        }
    }

    public void setCreditCardInformationAndSubmit(CreditCardInfo cardInfo) throws Exception {
        _driver.switchTo().frame(ccNumberIframe);
        _driver.findElement(By.id("credit-card-number")).sendKeys(cardInfo.getCcNumber());
        _driver.switchTo().defaultContent();

        _driver.findElement(By.id("cardholderName")).sendKeys(cardInfo.getCcHolder());

        _driver.switchTo().frame(ccExpirationMonthIframe);
        WebElement element = _driver.findElement(By.id("expiration-month"));
        new Select(element).selectByVisibleText(cardInfo.getCcMonth());
        _driver.switchTo().defaultContent();

        _driver.switchTo().frame(ccExpirationYearIframe);
        element = _driver.findElement(By.id("expiration-year"));
        new Select(element).selectByVisibleText(cardInfo.getCcYear());
        _driver.switchTo().defaultContent();

        _driver.switchTo().frame(ccCVVIframe);
        _driver.findElement(By.id("cvv")).sendKeys(cardInfo.getCcCVV());
        _driver.switchTo().defaultContent();

        btnPaymentSubmit.click();
    }

    public void setBankAccountInformationAndSubmit(BankAccountInfo accountInfo) throws Exception {
        new Select(drpAccountType).selectByVisibleText(accountInfo.getAccountType());
        txtRoutingNumber.sendKeys(accountInfo.getRoutingNumber());
        txtAccountNumber.sendKeys(accountInfo.getAccountNumber());
        txtFirstName.sendKeys(accountInfo.getFirstName());
        txtLastName.sendKeys(accountInfo.getLastName());
        txtStreet.sendKeys(accountInfo.getStreetAddress());
        txtCity.sendKeys(accountInfo.getCity());
        new Select(drpState).selectByVisibleText(accountInfo.getState());
        txtZip.sendKeys(accountInfo.getZipCode());

        btnPaymentSubmit.click();
    }

    public void clickNoCCSubmitBtn() throws Exception {
        btnSubmitPaymentNoCC.click();
    }

    public void clickCancelButton() throws Exception {
        btnCancel.click();
    }

    public void clearCreditCardFields() throws Exception {
        _driver.switchTo().frame(ccNumberIframe);
        _driver.findElement(By.id("credit-card-number")).clear();
        _driver.switchTo().defaultContent();

        _driver.findElement(By.id("cardholderName")).clear();

        _driver.switchTo().frame(ccCVVIframe);
        _driver.findElement(By.id("cvv")).clear();
        _driver.switchTo().defaultContent();
    }

    public void setChkUpgrade(boolean checked) throws Exception {
        if (checked) {
            if (!chkUpgrade.isSelected())
                chkUpgrade.click();
        }
    }

    public WebElement getTxtDate() {
        return txtDate;
    }

    public WebElement getTxtTime() {
        return txtTime;
    }

    public void setCheckInformation(CheckInfo check) throws Exception{
        txtCheckNumber.sendKeys(check.getCheckNumber());
        txtCheckAccountHolder.sendKeys(check.getAccountHolder());
    }

    public WebElement getTblPurchases() {
        return tblPurchases;
    }
}

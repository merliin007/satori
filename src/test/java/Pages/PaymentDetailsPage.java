package pages;

import base.CustomExceptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PaymentDetailsPage {

    @FindBy(how = How.ID, using = "__tab_ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpPurchases")
    private WebElement tabPurchase;
    @FindBy(how = How.ID, using = "__tab_ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation")
    private WebElement tabInformation;
    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_dvPayment_txtPaymentAmountValue")
    private WebElement txtAmount;
    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_dvPayment_lblCreatedOnValue")
    private WebElement lblDate;
    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpPurchases_grdPurchases")
    private WebElement tblDetailPurchase;
    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_dvPayment_lblPaymentMethodValue")
    private WebElement lblMethod;
    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_dvPayment_lblPaymentMethodValue")
    private WebElement lblStatus;
    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_btnCancel")
    private WebElement btnCancel;


    private WebDriver _driver;

    public PaymentDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    public WebElement getLblMethod() {
        return lblMethod;
    }

    public WebElement getTxtAmount() throws Exception{
        return txtAmount;
    }

    public WebElement getLblDate() {
        return lblDate;
    }

    public WebElement getTab(int i) throws CustomExceptions{
        switch (i){
            case 1:
                return tabInformation;
            case 2:
                return tabPurchase;
                default: throw new CustomExceptions("Option not found selecting tab!!");
        }
    }
    public void clickCancel() throws Exception{
        btnCancel.click();
    }

}

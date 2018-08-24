package pages.oldPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RefundPage {

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_lblBanner")
    private WebElement lblBanner;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_dvPayment_lblOriginalPurchaseAmount")
    private WebElement lblAmount;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_dvPayment_lblOriginalPurchasePaymentMethod")
    private WebElement lblMethod;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_dvPayment_ddlRefundMethod")
    private WebElement ddlRefundMethod;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_dvPayment_txtRefundDateValue")
    private WebElement txtRefundDate;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_btnCancel")
    private WebElement btnCancel;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_btnSave")
    private WebElement btnSave;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_tbc_tpInformation_dvPayment_txtComment")
    private WebElement txtComments;

    public RefundPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getLblBanner() throws Exception{
        return lblBanner;
    }

    public WebElement getLblAmount() throws Exception{
        return lblAmount;
    }

    public WebElement getLblMethod() throws Exception{
        return lblMethod;
    }

    public WebElement getDdlRefundMethod() throws Exception{
        return ddlRefundMethod;
    }

    public WebElement getTxtRefundDate() throws Exception{
        return txtRefundDate;
    }

    public WebElement getBtnSave() throws Exception{
        return btnSave;
    }

    public WebElement getBtnCancel() throws Exception{
        return btnCancel;
    }

    public WebElement getTxtComments() throws Exception{
        return txtComments;
    }

    public void setComments(String comments) throws Exception{
        this.txtComments.sendKeys(comments);
    }
}

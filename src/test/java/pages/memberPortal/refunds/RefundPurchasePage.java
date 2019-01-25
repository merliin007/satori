/*
 * Created by Miguel Angel Aguilar Cuevas
 * 17/01/2019 at 11:38 AM
 */
package pages.memberPortal.refunds;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class RefundPurchasePage {

    public RefundPurchasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_tbc_tpInformation_dvPurchase_ddlRefundMethod")
    private WebElement ddlRefundMethod;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_tbc_tpInformation_dvPurchase_txtRefundDateValue")
    private WebElement txtDate;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_tbc_tpInformation_dvPurchase_txtComment")
    private WebElement txtComments;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_tbc_tpInformation_dvPurchase_txtRefundAmount")
    private WebElement txtAmount;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_btnSave")
    private WebElement btnSave;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_btnCancel")
    private WebElement btnCancel;

    public WebElement getDdlRefundMethod() {
        return ddlRefundMethod;
    }

    public WebElement getTxtDate() {
        return txtDate;
    }

    public WebElement getTxtComments() {
        return txtComments;
    }

    public WebElement getTxtAmount() {
        return txtAmount;
    }

    public WebElement getBtnSave() {
        return btnSave;
    }

    public WebElement getBtnCancel() {
        return btnCancel;
    }
}

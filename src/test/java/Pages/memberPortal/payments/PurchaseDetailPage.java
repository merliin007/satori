/*
 * Created by Miguel Angel Aguilar Cuevas
 * 17/01/2019 at 12:58 PM
 */
package pages.memberPortal.payments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PurchaseDetailPage {

    public PurchaseDetailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPurchase_aShowChange")
    private WebElement btnChange;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPurchase_txtAmount")
    private WebElement txtAmount;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPurchase_txtComment")
    private WebElement txtComments;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_TabButtons_btnTBSave")
    private WebElement btnSave;

    //Membership fields
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPurchase_productSelector_ddlProductType")
    private WebElement ddlProductType;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPurchase_productSelector_ddlMembershipType")
    private WebElement ddlMembership;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPurchase_productSelector_cbIsUpgrade")
    private WebElement chkUpgrade;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPurchase_productSelector_ddlMembershipYear")
    private WebElement ddlYear;

    //Ladder fields
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPurchase_productSelector_ddlLadderYear")
    private WebElement ddlLadderYear;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPurchase_productSelector_ddlLadder")
    private WebElement ddlLadder;

    //Tournament fields
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPurchase_productSelector_ddlTournamentYear")
    private WebElement ddlTournamentYear;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPurchase_productSelector_ddlTournament")
    private WebElement ddlTournament;

    //Late Roster Fee
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpPurchase_productSelector_ddlLateRosterFeeYear")
    private WebElement ddlLateRosterYear;

    public WebElement getBtnChange() {
        return btnChange;
    }

    public WebElement getTxtAmount() {
        return txtAmount;
    }

    public WebElement getTxtComments() {
        return txtComments;
    }

    public WebElement getBtnSave() {
        return btnSave;
    }

    public WebElement getDdlProductType() {
        return ddlProductType;
    }

    public WebElement getDdlMembership() {
        return ddlMembership;
    }

    public WebElement getChkUpgrade() {
        return chkUpgrade;
    }

    public WebElement getDdlYear() {
        return ddlYear;
    }

    public WebElement getDdlLadderYear() {
        return ddlLadderYear;
    }

    public WebElement getDdlLadder() {
        return ddlLadder;
    }

    public WebElement getDdlTournamentYear() {
        return ddlTournamentYear;
    }

    public WebElement getDdlTournament() {
        return ddlTournament;
    }

    public WebElement getDdlLateRosterYear() {
        return ddlLateRosterYear;
    }
}

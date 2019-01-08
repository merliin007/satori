/*
 * Created by Miguel Angel Aguilar Cuevas
 * 29/08/2018 at 12:25 PM
 */
package pages.newPages.myRosters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NewRosterWizardPage {
    private WebDriver _driver;

    private By lblLocator = new By.ById("ctl00_ctl00_CPHolder_CPHolder_memberSearchPopup_lblMessage");

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ct999_dtv_lblAcknowledgement")
    private WebElement chkAcnowledgment;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ct999_dtv_btnSearchPlayers")
    private WebElement btnAddPlayer;

    @FindBy(how = How.CLASS_NAME, using = "wiz")
    private WebElement divWiz;

    @FindBy(how = How.CLASS_NAME, using = "stepcontent")
    private WebElement divStepContent;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnNext")
    private WebElement btnNext;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnCancel")
    private WebElement btnCancel;

    @FindBy(how = How.CLASS_NAME, using = "modal-content")
    private WebElement addPlayerModal;

    private By playerModalLocator = new By.ByClassName("modal-content");

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ct999_dtv_ddlCap")
    private WebElement ddlCaptain;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ct999_dtv_ddlCoCap")
    private WebElement ddlCoCaptain;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ct999_dtv_btnSearchDesignees")
    private WebElement btnDesignee;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ct999_dtv_btnSearchFacility")
    private WebElement btnFacility;

    @FindBy(how = How.ID, using = "facilitysearch-popup")
    private WebElement addFacilityModal;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ct999_dtv_ddlLevelFlight")
    private WebElement ddlLevelFlight;

    private By divBusyPopupLocator = new By.ById("ctl00_ctl00_CPHolder_CPHolder_wizardIsBusyIndicator_divBusyPopup");

    public NewRosterWizardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    public WebElement getChkAcnowledgment() {
        return chkAcnowledgment;
        //return _driver.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_ct999_dtv_cbAcknowledgement"));
    }

    public WebElement getBtnAddPlayer() {
        return btnAddPlayer;
    }

    public WebElement getICertifyGrantedPermission() {
        return divWiz.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_ct999_dtv_cbAcknowledgement"));
    }
    public WebElement getICertifyGrantedFacilityPermission(){
        return divWiz.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_ct999_dtv"))
                .findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_ct999_dtv_cbAcknowledgement"));
    }

    public WebElement getBtnNext() {
        return divWiz.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_udpWizard"))
                .findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_spnButtonsNormal"))
                .findElement(By.name("ctl00$ctl00$CPHolder$CPHolder$btnNext"));

        /*return _driver.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_udpWizard"))
                .findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_btnNext"));*/
    }

    public WebElement getBtnCancel() {
        return btnCancel;
    }

    public List<WebElement> getAddPlayerModalTblResults() {
        return addPlayerModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_memberSearchPopup_tableDataView_grdTableList"))
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
    }

    public List<WebElement> getAddFacilityModalTblResults() {
        return addFacilityModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_facilitySearchPopup_tableDataView_grdTableList"))
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
    }

    public WebElement getAddPlayerModalElement(String el) {
        switch (el) {
            case "txtalta":
                return addPlayerModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_memberSearchPopup_txtAltaNumber"));
            case "txtfirst":
                return addPlayerModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_memberSearchPopup_txtFirstName"));
            case "txtlast":
                return addPlayerModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_memberSearchPopup_txtLastName"));
            case "btnSearch":
                return addPlayerModal.findElement(By.name("ctl00$ctl00$CPHolder$CPHolder$memberSearchPopup$btnSearch"));
            case "spinner":
                return addPlayerModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_memberSearchPopup_imgBusy"));
            case "lblMessage":
                return addPlayerModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_memberSearchPopup_lblMessage"));
            case "bntclose":
                return addPlayerModal.findElement(By.className("close"));
            default:
                return null;
        }
    }

    public WebElement getAddFacilityModalElement(String el) {
        switch (el) {
            case "txtName":
                return addFacilityModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_facilitySearchPopup_txtName"));
            case "txtCity":
                return addFacilityModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_facilitySearchPopup_txtCity"));
            case "ddlCounty":
                return addFacilityModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_facilitySearchPopup_ddlCounty"));
            case "btnSearch":
                return addFacilityModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_facilitySearchPopup_btnSearch"));
            case "lblMessage":
                return addFacilityModal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_facilitySearchPopup_lblMessage"));
            case "btnClose":
                return addFacilityModal.findElement(By.className("close"));
            default:
                return null;
            //ctl00_ctl00_CPHolder_CPHolder_facilitySearchPopup_updSearchResults
        }
    }

    public WebElement getAddPlayerModal() {
        return addPlayerModal;
    }

    public By getAddPlayerLocator(){
        return playerModalLocator;
    }

    public WebElement getDdlCaptain() {
        return ddlCaptain;
    }

    public WebElement getDdlCoCaptain() {
        return ddlCoCaptain;
    }

    public By getLblLocator() {
        return lblLocator;
    }

    public By getPlayerModalLocator() {
        return playerModalLocator;
    }

    public WebElement getBtnFacility() {
        return btnFacility;
    }

    public WebElement getAddFacilityModal() {
        return addFacilityModal;
    }

    public WebElement getDdlLevelFlight() {
        return ddlLevelFlight;
    }

    public By getDivBusyPopupLocator() {
        return divBusyPopupLocator;
    }

    public WebElement getDivStepContent() {
        return divStepContent;
    }

    public WebElement getWizardObscurer(){
        return _driver.findElement(By.id("WizardIsBusyIndicatorBehavior_backgroundElement"));
    }

    public WebElement getBtnDesignee() {
        return btnDesignee;
    }
}

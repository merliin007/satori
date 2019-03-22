/*
 * Created by Miguel Angel Aguilar Cuevas
 * 15/08/2018 at 11:38 PM
 */
package pages.leagues;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LeagueNewEditPage {
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_ddlYear")
    private WebElement ddlYear;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_ddlSeasons")
    private WebElement ddlSeason;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_ddlPlayDay")
    private WebElement ddlPlayDay;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_ddlLeagueMinimumAgeType")
    private WebElement ddlMinAgeType;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_txtMinAge")
    private WebElement txtMinAge;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_txtMaxAge")
    private WebElement txtMaxAge;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_ddlScoreCard")
    private WebElement ddlScoreCard;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_chkLights")
    private WebElement chkLights;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_chkThirdSetTiebreaker")
    private WebElement chkTieBreaker;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_btnVPSelect")
    private WebElement btnVPSelect;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_btnRosterInstructionsDocumentSelect")
    private WebElement btnRosterInstructions;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_btnCaptainsPacketDocumentSelect")
    private WebElement btnPacketDocument;

    @FindBy(how = How.ID, using = "__tab_ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription")
    private WebElement tabDescription;
    @FindBy(how = How.ID, using = "__tab_ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpLeagueDetails")
    private WebElement tabDetails;
    @FindBy(how = How.ID, using = "__tab_ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDates")
    private WebElement tabDates;
    @FindBy(how = How.ID, using = "__tab_ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpExclusions")
    private WebElement tabExclusions;

    @FindBy(how =How.ID, using = "__tab_ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpErrors")
    private WebElement tabErrors;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDates_txtRegistrationEnd")
    private WebElement txtRegEndDate;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDates_txtCaptain")
    private WebElement txtCaptMeetingDate;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDates_txtFirstDate")
    private WebElement txtFirstWeekPlay;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDates_btnCalculate")
    private WebElement btnCalculateDates;

    @FindBy (how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDates_lstEvents_ctrl20_ctl01_txtDateTimeValue")
    private WebElement postSeasonDeletionLocator;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnSave")
    private WebElement btnSave;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnCancel")
    private WebElement btnCancel;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpErrors")
    private WebElement tblErrors;


    public LeagueNewEditPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getDdlYear() {
        return ddlYear;
    }

    public WebElement getDdlSeason() {
        return ddlSeason;
    }

    public WebElement getDdlPlayDay() {
        return ddlPlayDay;
    }

    public WebElement getDdlMinAgeType() {
        return ddlMinAgeType;
    }

    public WebElement getTxtMinAge() {
        return txtMinAge;
    }

    public WebElement getTxtMaxAge() {
        return txtMaxAge;
    }

    public WebElement getDdlScoreCard() {
        return ddlScoreCard;
    }

    public WebElement getChkLights() {
        return chkLights;
    }

    public WebElement getChkTieBreaker() {
        return chkTieBreaker;
    }

    public WebElement getBtnVPSelect() {
        return btnVPSelect;
    }

    public WebElement getBtnRosterInstructions() {
        return btnRosterInstructions;
    }

    public WebElement getBtnPacketDocument() {
        return btnPacketDocument;
    }

    public WebElement getTabDescription() {
        return tabDescription;
    }

    public WebElement getTabDetails() {
        return tabDetails;
    }

    public WebElement getTabDates() {
        return tabDates;
    }

    public WebElement getTabErrors() {
        return tabErrors;
    }

    public WebElement getTabExclusions() {
        return tabExclusions;
    }

    public WebElement getBtnSave() {
        return btnSave;
    }

    public WebElement getBtnCancel() {
        return btnCancel;
    }

    public WebElement getTxtRegEndDate() {
        return txtRegEndDate;
    }

    public WebElement getTxtCaptMeetingDate() {
        return txtCaptMeetingDate;
    }

    public WebElement getTxtFirstWeekPlay() {
        return txtFirstWeekPlay;
    }

    public WebElement getBtnCalculateDates() {
        return btnCalculateDates;
    }

    public WebElement getPostSeasonDeletionLocator() {
        return postSeasonDeletionLocator;
    }

    public WebElement getTblErrors() {
        return tblErrors;
    }
}

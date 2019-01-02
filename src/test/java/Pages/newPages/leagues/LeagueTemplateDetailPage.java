/*
 * Created by Miguel Angel Aguilar Cuevas
 * 02/01/2019 at 11:31 AM
 */
package pages.newPages.leagues;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LeagueTemplateDetailPage {

    WebDriver _driver;

    public LeagueTemplateDetailPage(WebDriver driver) {
        PageFactory.initElements(driver, this); _driver = driver;
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_tab")
    private WebElement tabDescription;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDetails_tab")
    private WebElement tabDetails;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpSeasons_tab")
    private WebElement tabSeasons;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpTemplateExclusion_tab")
    private WebElement tabExclusions;

    private By tabErrors = By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpErrors_tab");

//    DESCRIPTION SECTION

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_txtDescription")
    private WebElement txtLeagueName;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_ddlGender")
    private WebElement dropGender;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_ddlLeagueMinimumAgeType")
    private WebElement dropMinAgeType;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_txtMinAge")
    private WebElement txtMinAge;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_txtMaxAge")
    private WebElement txtMaxAge;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_ddlScoreCard")
    private WebElement dropScoreCardType;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_txtNumberOfSeasonsPerYearCredit")
    private WebElement txtNumberOfSeasons;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDescription_lblActiveFlag")
    private WebElement chkIsActive;

//    DETAILS SECTION

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDetails_txtMinTeamMembers")
    private WebElement txtMinTeamMembers;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDetails_txtMinReturningTeamMembers")
    private WebElement txtMinRetTeamMembers;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDetails_txtMinMatchPlayers")
    private WebElement txtMinMatchPlayers;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDetails_txtMaxAddOns")
    private WebElement txtMaxAddons;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDetails_txtMinFemales")
    private WebElement txtMinFemales;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDetails_txtMinReturningFemales")
    private WebElement txtMinRetFemales;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDetails_txtMinMales")
    private WebElement txtMinMales;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDetails_txtMinReturningMales")
    private WebElement txtMinRetMales;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDetails_txtMinTeamMembersForCalculations")
    private WebElement txtMinTeamMembersNeeded;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDetails_txtMinFemalesForCalculations")
    private WebElement txtMinFemalesNeeded;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpDetails_txtMinMalesForCalculations")
    private WebElement txtMinMalesNeeded;

//    SEASON SECTION

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpSeasons_chkSeasons")
    private WebElement tblSeasonsChk;

//    EXCLUSIONS SECTION

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_tbc_tpTemplateExclusion_chkExclusions")
    private WebElement tblExclusionsChk;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnSave")
    private WebElement btnSave;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnCancel")
    private WebElement btnCancel;

    public WebElement getTabDescription() {
        return tabDescription;
    }

    public WebElement getTabDetails() {
        return tabDetails;
    }

    public WebElement getTabSeasons() {
        return tabSeasons;
    }

    public WebElement getTabExclusions() {
        return tabExclusions;
    }

    public By getTabErrorsLocator() {
        return tabErrors;
    }

    public WebElement getTabErrors(){
        return _driver.findElement(getTabErrorsLocator());
    }

    public WebElement getTxtLeagueName() {
        return txtLeagueName;
    }

    public WebElement getDropGender() {
        return dropGender;
    }

    public WebElement getDropMinAgeType() {
        return dropMinAgeType;
    }

    public WebElement getTxtMinAge() {
        return txtMinAge;
    }

    public WebElement getTxtMaxAge() {
        return txtMaxAge;
    }

    public WebElement getDropScoreCardType() {
        return dropScoreCardType;
    }

    public WebElement getTxtNumberOfSeasons() {
        return txtNumberOfSeasons;
    }

    public WebElement getChkIsActive() {
        return chkIsActive;
    }

    public WebElement getTxtMinTeamMembers() {
        return txtMinTeamMembers;
    }

    public WebElement getTxtMinRetTeamMembers() {
        return txtMinRetTeamMembers;
    }

    public WebElement getTxtMinMatchPlayers() {
        return txtMinMatchPlayers;
    }

    public WebElement getTxtMaxAddons() {
        return txtMaxAddons;
    }

    public WebElement getTxtMinFemales() {
        return txtMinFemales;
    }

    public WebElement getTxtMinRetFemales() {
        return txtMinRetFemales;
    }

    public WebElement getTxtMinMales() {
        return txtMinMales;
    }

    public WebElement getTxtMinRetMales() {
        return txtMinRetMales;
    }

    public WebElement getTxtMinTeamMembersNeeded() {
        return txtMinTeamMembersNeeded;
    }

    public WebElement getTxtMinFemalesNeeded() {
        return txtMinFemalesNeeded;
    }

    public WebElement getTxtMinMalesNeeded() {
        return txtMinMalesNeeded;
    }

    public WebElement getTblSeasonsChk() {
        return tblSeasonsChk;
    }

    public WebElement getTblExclusionsChk() {
        return tblExclusionsChk;
    }

    public WebElement getBtnSave() {
        return btnSave;
    }

    public WebElement getBtnCancel() {
        return btnCancel;
    }

    public List<WebElement> getSeasonChecks(){
        return getTblSeasonsChk().findElements(By.tagName("tr"));

    }

    public List<WebElement> getExclusionChecks(){
        return getTblExclusionsChk().findElements(By.tagName("tr"));
    }
}

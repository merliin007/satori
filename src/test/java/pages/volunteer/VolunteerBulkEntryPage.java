/*
 * Created by Miguel Angel Aguilar Cuevas
 * 08/01/2019 at 2:47 PM
 */
package pages.volunteer;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class VolunteerBulkEntryPage {

    private WebDriver _driver;

    public VolunteerBulkEntryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_txtAltaNum")
    private WebElement txtAltaNumber;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_btnSearch")
    private WebElement btnSearch;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_btnReset")
    private WebElement btnClear;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_ddlVolunteerType")
    private WebElement ddlVolunteerPosition;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_ddlYears")
    private WebElement ddlYear;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_ddlLeagueType")
    private WebElement ddlLeague;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_ddlSeason")
    private WebElement ddlSeason;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_txtComments")
    private WebElement txtComments;

    @FindBy(how= How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_btnAddNew")
    private WebElement btnSave;

    private By toastrBy = By.id("toast-container");

    public WebElement getTxtAltaNumber() {
        return txtAltaNumber;
    }

    public WebElement getBtnSearch() {
        return btnSearch;
    }

    public WebElement getBtnClear() {
        return btnClear;
    }

    public By getToastrBy() {
        return toastrBy;
    }

    public By getModalLocator() {
        return By.className("modal-content");
    }

    public WebElement getModal() {
        return _driver.findElement(getModalLocator());
    }

    public By getYearLocator() {
        return By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_ddlYears");
    }

    public By getLeagueLocator() {
        return By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_ddlLeagueType");
    }

    public By getSeasonLocator() {
        return By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_ddlSeason");
    }

    public By getCommentsLocator() {
        return By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_txtComments");
    }

    public By getToastMessage(String msg) {
        switch (msg) {
            case "error":
                return By.className("toast-error");
            case "warn":
                return By.className("toast-warning");
            case "success":
                return By.className("toast-success");
            default:
                return null;
        }
    }

    public WebElement getToast(By locator) {
        return _driver.findElement(locator);
    }

    public WebElement getDdlVolunteerPosition() {
        return ddlVolunteerPosition;
    }

    public WebElement getDdlYear() {
        return ddlYear;
    }

    public WebElement getDdlLeague() {
        return ddlLeague;
    }

    public WebElement getDdlSeason() {
        return ddlSeason;
    }

    public WebElement getTxtComments() {
        return txtComments;
    }

    public WebElement getBtnSave() {
        return btnSave;
    }
}

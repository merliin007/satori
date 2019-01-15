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

    public WebElement getSaveModal() {
        return _driver.findElement(By.id("assignVolunteer"))
                .findElement(By.className("modal-content"))
                .findElement(By.className("modal-footer"))
                .findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_btnAltaNumAdd"));
    }

    public WebElement getPositionModal() {
        return _driver.findElement(By.id("assignVolunteer"))
                .findElement(By.className("modal-content"))
                .findElement(By.className("modal-body"))
                .findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_ddlVolunteerType"));
    }

    public WebElement getYearModal() {
        return _driver.findElement(By.id("assignVolunteer"))
                .findElement(By.className("modal-content"))
                .findElement(By.className("modal-body"))
                .findElement(getYearLocator());

    }

    public By getYearLocator() {
        return By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_ddlYears");
    }

    public WebElement getLeagueModal() {
        return _driver.findElement(By.id("assignVolunteer"))
                .findElement(By.className("modal-content"))
                .findElement(By.className("modal-body"))
                .findElement(getLeagueLocator());
    }

    public By getLeagueLocator() {
        return By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_ddlLeagueType");
    }

    public WebElement getSeasonModal() {
        return _driver.findElement(By.id("assignVolunteer"))
                .findElement(By.className("modal-content"))
                .findElement(By.className("modal-body"))
                .findElement(getSeasonLocator());
    }

    public By getSeasonLocator() {
        return By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_ddlSeason");
    }

    public WebElement getCommentsModal() {
        return _driver.findElement(By.id("assignVolunteer"))
                .findElement(By.className("modal-content"))
                .findElement(By.className("modal-body"))
                .findElement(getCommentsLocator());
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

    public WebElement getToast(By locator){
        return _driver.findElement(locator);
    }

}

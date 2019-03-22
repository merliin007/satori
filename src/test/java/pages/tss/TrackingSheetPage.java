/*
 * Created by Miguel Angel Aguilar Cuevas
 * 10/01/2019 at 1:51 PM
 */
package pages.tss;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pages.Pages;

import java.util.List;

public class TrackingSheetPage extends Pages {
    public TrackingSheetPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_ddlStatus")
    private WebElement ddlStatus;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_txtYear")
    private WebElement txtYear;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_ddlSeason")
    private WebElement ddlSeason;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_ddlLeagueType")
    private WebElement ddlLeague;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_ddlAgeGroup")
    private WebElement ddlAge;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_ddlScorecardStatus")
    private WebElement ddlOriginalScorecard;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_ddlLevelFlight")
    private WebElement ddlLevelFlight;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_ddlDivision")
    private WebElement ddlDivision;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_ddlWeek")
    private WebElement ddlWeek;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_txtRosterID")
    private WebElement txtRosterId;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_btnFilter")
    private WebElement btnSearch;
    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_btnReset")
    private WebElement btnClear;
    private By toastContainerLocator = By.id("toast-container");


    public WebElement getDdlStatus() {
        return ddlStatus;
    }

    public WebElement getTxtYear() {
        return txtYear;
    }

    public WebElement getDdlSeason() {
        return ddlSeason;
    }

    public WebElement getDdlLeague() {
        return ddlLeague;
    }

    public WebElement getDdlAge() {
        return ddlAge;
    }

    public WebElement getDdlOriginalScorecard() {
        return ddlOriginalScorecard;
    }

    public WebElement getDdlLevelFlight() {
        return ddlLevelFlight;
    }

    public WebElement getDdlDivision() {
        return ddlDivision;
    }

    public WebElement getDdlWeek() {
        return ddlWeek;
    }

    public WebElement getTxtRosterId() {
        return txtRosterId;
    }

    public WebElement getBtnSearch() {
        return btnSearch;
    }

    public WebElement getBtnClear() {
        return btnClear;
    }

    public By getToastContainerLocator() {
        return toastContainerLocator;
    }

    public String getToastrMesage(){
        return _driver.findElement(toastContainerLocator).findElement(By.className("toast-message")).getText();
    }

    @Override
    public int getPageActionIndex(String action) {
        return 0;
    }

    @Override
    public int getIndexForHeader(String columnName) {
        return 0;
    }

    @Override
    public List<WebElement> getAllColumnHeaders() {
        return null;
    }
}

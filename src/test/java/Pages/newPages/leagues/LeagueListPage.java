/*
 * Created by Miguel Angel Aguilar Cuevas
 * 15/08/2018 at 5:06 PM
 */
package pages.newPages.leagues;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pages.newPages.Pages;

import java.util.List;

public class LeagueListPage extends Pages {

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_txtYear")
    private WebElement txtYear;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlSeason")
    private WebElement ddlSeason;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlLeagueType")
    private WebElement ddlLeagueType;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlPlayDay")
    private WebElement ddlPlayDay;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnFilter")
    private WebElement btnSearch;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_btnNew")
    private WebElement btnNewLeague;

    @FindBy(how = How.CLASS_NAME, using = "modal-content")
    private WebElement deleteModal;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_lnkConfirm")
    private WebElement btnConfirmDelete;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_lnkTemplates")
    private WebElement tabLeagueTemplate;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_lnkLeague")
    private WebElement tabLeagues;



    public LeagueListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getTxtYear() {
        return txtYear;
    }

    public WebElement getDdlSeason() {
        return ddlSeason;
    }

    public WebElement getDdlLeagueType() {
        return ddlLeagueType;
    }

    public WebElement getDdlPlayDay() {
        return ddlPlayDay;
    }

    public WebElement getBtnSearch() {
        return btnSearch;
    }

    public WebElement getBtnNewLeague() {
        return btnNewLeague;
    }

    public WebElement getDeleteModal() {
        return deleteModal;
    }

    public WebElement getBtnConfirmDelete() {
        return btnConfirmDelete;
    }

    @Override
    public int getPageActionIndex(String action) {
        switch (action.toLowerCase()) {
            case "select":
                return 0;
            case "delete":
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public int getIndexForHeader(String columnName) {
        return 0;
    }

    @Override
    public List<WebElement> getAllColumnHeaders() {
        return null;
    }

   /* @Override
    public List<WebElement> getTblResults(int i) {
        return null;
    }*/

    public WebElement getTabLeagueTemplate() {
        return tabLeagueTemplate;
    }

    public WebElement getTabLeagues() {
        return tabLeagues;
    }
}

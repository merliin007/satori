/*
 * Created by Miguel Angel Aguilar Cuevas
 * 17/08/2018 at 3:39 PM
 */
package pages.leagues;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LeagueVP_SelectionPage {
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_memberSearch_txtAltaNumber")
    private WebElement txtALTANumber;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_memberSearch_btnFilter")
    private WebElement btnSearch;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_memberSearch_tableDataView_pnlGrid")
    private WebElement tblTableResults;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_memberSearch_btnCancel")
    private WebElement btnCancel;

    public WebElement getTblLeagueTemplaes() {
        return tblTableResults;
    }

    public WebElement getTxtALTANumber() {
        return txtALTANumber;
    }

    public WebElement getBtnSearch() {
        return btnSearch;
    }

    public List<WebElement> getTblResults(int i) {
        return tblTableResults.findElements(By.id("ctl00_ctl00_CPHolder_CPHolder_memberSearch_tableDataView_grdTableList"))
                .get(i)
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
    }

    public LeagueVP_SelectionPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getBtnCancel() {
        return btnCancel;
    }
}

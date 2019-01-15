/*
 * Created by Miguel Angel Aguilar Cuevas
 * 19/08/2018 at 11:37 PM
 */
package pages.documents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RosterInstructions_SelectionPage {
    @FindBy(how = How.ID, using = "ctl00$ctl00$CPHolder$CPHolder$documentSearch$txtDocumentID")
    private WebElement txtDocmentID;

    @FindBy(how = How.ID, using = "ctl00$ctl00$CPHolder$CPHolder$documentSearch$btnFilter")
    private WebElement btnSearch;

    @FindBy(how =How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_documentSearch_tableDataView_pnlGrid")
    private WebElement tblTableResults;

    public WebElement getTxtDocmentID() {
        return txtDocmentID;
    }

    public WebElement getBtnSearch() {
        return btnSearch;
    }

    public WebElement getTblTableResults() {
        return tblTableResults;
    }

    public List<WebElement> getTblResults(int i) {
        return tblTableResults.findElements(By.id("ctl00_ctl00_CPHolder_CPHolder_documentSearch_tableDataView_grdTableList"))
                .get(i)
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
    }
    public RosterInstructions_SelectionPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

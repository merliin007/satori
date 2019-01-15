/*
 * Created by Miguel Angel Aguilar Cuevas
 * 29/08/2018 at 12:23 PM
 */
package pages.myRosters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pages.Pages;

import java.util.List;

public class NewRosterPage extends Pages {

    public NewRosterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_NewRosterControl_newRosterChoice_pnlPopup")
    private WebElement newRosterModal;

    @Override
    public int getPageActionIndex(String action) {
        switch (action.toLowerCase()) {
            case "select":
                return 0;
            case "old":
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

    public List<WebElement> getTblResults(int i) {
        List<WebElement> tbl = _driver.findElements(By.id("ctl00_ctl00_CPHolder_CPHolder_NewRosterControl_tableDataView_grdTableList"))
                .get(1)
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
        return tbl;
    }

    public WebElement getNewRosterModal() {
        return newRosterModal;
    }

    public WebElement modalCommands(String btn) {
        WebElement modal = getNewRosterModal();
        switch (btn.toLowerCase()) {
            case "new":
                return modal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_NewRosterControl_newRosterChoice_btnNew"));
            case "reinstate":
                return modal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_NewRosterControl_newRosterChoice_btnReinstate"));
            case "close":
                return modal.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_NewRosterControl_newRosterChoice_btnClosePop"));
            default:
                return null;
        }
    }
}

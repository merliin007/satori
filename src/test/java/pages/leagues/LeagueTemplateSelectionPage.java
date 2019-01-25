/*
 * Created by Miguel Angel Aguilar Cuevas
 * 15/08/2018 at 5:36 PM
 */
package pages.leagues;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LeagueTemplateSelectionPage {

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_pnlGrid")
    private WebElement tblTableResults;

    private By toastrBy = By.id("toast-container");

    public By getToastrBy() {
        return toastrBy;
    }

    public WebElement getTblLeagueTemplaes() {
        return tblTableResults;
    }

    public LeagueTemplateSelectionPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getTblResults(int i) {
        return tblTableResults.findElements(By.id("ctl00_ctl00_CPHolder_CPHolder_tableDataView_grdTableList"))
                .get(i)
                .findElement(By.tagName("tbody"))
                .findElements(By.tagName("tr"));
    }

}

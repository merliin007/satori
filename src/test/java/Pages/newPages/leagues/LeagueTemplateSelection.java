/*
 * Created by Miguel Angel Aguilar Cuevas
 * 15/08/2018 at 5:36 PM
 */
package pages.newPages.leagues;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LeagueTemplateSelection {

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_tableDataView_grdTableList")
    private WebElement tblLeagueTemplaes;

    public WebElement getTblLeagueTemplaes() {
        return tblLeagueTemplaes;
    }

    public LeagueTemplateSelection(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getTemplateRow(String leagueType){
        List<WebElement> rows = tblLeagueTemplaes.findElements(By.tagName("tr"));
    }

}

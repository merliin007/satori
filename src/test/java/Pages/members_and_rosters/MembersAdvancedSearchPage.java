/*
 * Created by Miguel Angel Aguilar Cuevas
 * 04/01/2019 at 3:06 PM
 */
package pages.members_and_rosters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pages.Pages;

import java.util.List;

public class MembersAdvancedSearchPage extends Pages {

    public MembersAdvancedSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_txtAltaNumber")
    private WebElement txtAltaNumber;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnFilter")
    private WebElement btnSearch;

    public WebElement getTxtAltaNumber() {
        return txtAltaNumber;
    }

    public WebElement getBtnSearch() {
        return btnSearch;
    }

    @Override
    public int getPageActionIndex(String action) {
        switch (action.toLowerCase()){
            case "select": return 0;
            case "play history": return 1;
            case "impersonate": return 2;
            case "delete": return 3;
            default: return -1;
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
}

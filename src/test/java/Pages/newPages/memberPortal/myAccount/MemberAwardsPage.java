/*
 * Created by Miguel Angel Aguilar Cuevas
 * 08/01/2019 at 11:52 AM
 */
package pages.newPages.memberPortal.myAccount;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pages.newPages.Pages;

import java.util.List;

public class MemberAwardsPage extends Pages {

    public MemberAwardsPage(WebDriver _driver) {
        super(_driver);
        PageFactory.initElements(_driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_AwardList1_btnNew")
    private WebElement btnNewAward;

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

    public WebElement getBtnNewAward() {
        return btnNewAward;
    }
}

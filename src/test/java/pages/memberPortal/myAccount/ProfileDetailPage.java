/*
 * Created by Miguel Angel Aguilar Cuevas
 * 04/01/2019 at 4:20 PM
 */
package pages.memberPortal.myAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pages.Pages;

import java.util.List;

public class ProfileDetailPage extends Pages {

    public ProfileDetailPage(WebDriver _driver) {
        super(_driver);
        PageFactory.initElements(_driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ctl01_dtv_tbc_tpAddress_txtStreet")
    private WebElement txtAddress;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ctl01_btnSave")
    private WebElement btnSave;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ctl01_btnCancel")
    private WebElement btnCancel;

    private By toastrBy = By.id("toast-container");

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


    public WebElement getTabContainer() {
        return _driver.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_ctl01_pnlProfile"))
                .findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_ctl01_dtv"))
                .findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_ctl01_dtv_tbc_header"));

    }

    public WebElement getTab(String tabOpt){
        List<WebElement> tabs = getTabContainer().findElements(By.className("ajax__tab_inner"));
        for (WebElement tab: tabs){
            if(tab.findElement(By.tagName("a")).getText().toLowerCase().equals(tabOpt.toLowerCase()))
                return tab;
        }
        return null;
    }

    public WebElement getTxtAddress() {
        return txtAddress;
    }

    public WebElement getBtnSave() {
        return btnSave;
    }

    public WebElement getBtnCancel() {
        return btnCancel;
    }

    public By getToastrBy() {
        return toastrBy;
    }
}

/*
 * Created by Miguel Angel Aguilar Cuevas
 * 08/01/2019 at 11:14 AM
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

public class MemberPreferencesPage extends Pages {

    public MemberPreferencesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_MyPreferences1_dtv")
    private WebElement shareContainer;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_MyPreferences1_btnTBSave")
    private WebElement btnSave;

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

    public WebElement getChkShare() {
        return shareContainer.findElement(By.tagName("tbody"))
                .findElements(By.className("prefs__item"))
                .get(0)
                .findElement(By.className("custom-checkbox"))
                .findElement(By.tagName("label"));
    }

    public WebElement getBtnSave() {
        return btnSave;
    }

    public By getToastrBy() {
        return toastrBy;
    }
}

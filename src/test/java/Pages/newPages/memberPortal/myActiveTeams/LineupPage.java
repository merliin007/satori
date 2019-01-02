/*
 * Created by Miguel Angel Aguilar Cuevas
 * 27/12/2018 at 4:40 PM
 */
package pages.newPages.memberPortal.myActiveTeams;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LineupPage {

    public LineupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnTBPublish")
    private WebElement btnPublish;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnSave")
    private WebElement btnSave;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnTBCancel")
    private WebElement btnCancel;

    public WebElement getBtnPublish() {
        return btnPublish;
    }

    public WebElement getBtnSave() {
        return btnSave;
    }

    public WebElement getBtnCancel() {
        return btnCancel;
    }
}

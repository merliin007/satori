/*
 * Created by Miguel Angel Aguilar Cuevas
 * 28/12/2018 at 4:01 PM
 */
package pages.newPages.memberPortal.myActiveTeams;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LeaderPreferencesPage {

    public LeaderPreferencesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_LineOptions")
    private WebElement lineOptions;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnTBSave")
    private WebElement btnSave;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnTBCancel")
    private WebElement btnCancel;

//    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv_TLPFoodRadio_0")
//    private WebElement rdioLine;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_dtv")
    private WebElement table;

    private By toastrBy = By.id("toast-container");

    public WebElement getBtnSave() {
        return btnSave;
    }

    public WebElement getBtnCancel() {
        return btnCancel;
    }

    public List<WebElement> getLineOptions() {
        return lineOptions.findElements(By.tagName("input"));
    }

    public WebElement getRdioLine() {
        return table.findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_dtv_TLPFoodRadio_0"));
    }

    public By getToastrBy() {
        return toastrBy;
    }
}

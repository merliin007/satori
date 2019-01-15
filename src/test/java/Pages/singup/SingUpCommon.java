/*
 * Created by Miguel Angel Aguilar Cuevas
 * 09/01/2019 at 11:26 AM
 */
package pages.singup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SingUpCommon {
    public SingUpCommon(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_CPHolder_TabButtonsControl1_btnTBPrev")
    private WebElement btnPrevius;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_TabButtonsControl1_btnTBNext")
    private WebElement btnNext;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_TabButtonsControl1_btnTBSave")
    private WebElement btnSave;
    @FindBy(how = How.ID, using = "ctl00_CPHolder_TabButtonsControl1_btnTBCancel")
    private WebElement btnCancel;

    public WebElement getBtnPrevius() {
        return btnPrevius;
    }

    public WebElement getBtnNext() {
        return btnNext;
    }

    public WebElement getBtnSave() {
        return btnSave;
    }

    public WebElement getBtnCancel() {
        return btnCancel;
    }
}

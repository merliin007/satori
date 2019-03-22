/*
 * Created by Miguel Angel Aguilar Cuevas
 * 09/01/2019 at 11:12 AM
 */
package pages.singup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class NewMemberSignUpPage {

    public NewMemberSignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using ="ctl00_CPHolder_txtFirstName")
    private WebElement txtFirstName;

    @FindBy(how = How.ID, using ="ctl00_CPHolder_txtLastName")
    private WebElement txtLastName;

    @FindBy(how = How.ID, using ="ctl00_CPHolder_btnNext")
    private WebElement btnNext;

    public WebElement getTxtFirstName() {
        return txtFirstName;
    }

    public WebElement getTxtLastName() {
        return txtLastName;
    }

    public WebElement getBtnNext() {
        return btnNext;
    }
}

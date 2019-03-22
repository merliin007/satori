/*
 * Created by Miguel Angel Aguilar Cuevas
 * 16/01/2019 at 11:29 AM
 */
package pages.memberPortal.payments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentConfirmationPage {

    public PaymentConfirmationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_lblConfirmationNumberValue")
    private WebElement lblConfirmation;

    @FindBy(how = How.CLASS_NAME, using ="container")
    private WebElement container;


    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_Button1")
    private WebElement btnContinue;

    public WebElement getContainer() {
        return container;
    }

    public List<WebElement> tblResults(){
        return container.findElement(By.className("roster-summary")).findElements(By.tagName("tr"));
    }

    public WebElement getLblConfirmation() {
        return lblConfirmation;
    }

    public WebElement getBtnContinue() {
        return btnContinue;
    }
}

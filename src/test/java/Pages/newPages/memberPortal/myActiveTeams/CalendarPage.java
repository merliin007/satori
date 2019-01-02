/*
 * Created by Miguel Angel Aguilar Cuevas
 * 28/12/2018 at 12:18 PM
 */
package pages.newPages.memberPortal.myActiveTeams;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CalendarPage {

//    private WebDriver _driver;

    public CalendarPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
//        _driver = driver;
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnNewEvent")
    private WebElement btnNewEvent;

    @FindBy(how = How.CLASS_NAME, using = "modal-content")
    private WebElement modalEvent;

    private By toastrBy = By.id("toast-container");

    public WebElement getBtnNewEvent() {
        return btnNewEvent;
    }

    public WebElement getModalEvent() {
        return modalEvent;
    }

    public WebElement getModalObject(String name) {
        switch (name.toLowerCase()) {
            case "name":
                return getModalEvent().findElement(By.id("eventname"));
            case "description":
                return getModalEvent().findElement(By.id("eventdesc"));
            case "date":
                return getModalEvent().findElement(By.id("eventdate"));
            case "submit":
                return getModalEvent().findElement(By.id("ctl00_ctl00_CPHolder_CPHolder_lnkConfirm"));
            case "cancel":
                return getModalEvent().findElement(By.className("btn-outline-danger"));
            default:
                return null;
        }
    }

    public By getToastrBy() {
        return toastrBy;
    }
}

package pages.newPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class nMainPage {

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_LblWelcome")
    private WebElement lblWelcome;

    @FindBy(how = How.ID, using = "welcomeContainer")
    private WebElement lblContainer;

    public nMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getLblWelcome() {
        return lblWelcome;
    }

    public WebElement getLblContainer() {
        return lblContainer;
    }
}

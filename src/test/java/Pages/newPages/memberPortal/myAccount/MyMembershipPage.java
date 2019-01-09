/*
 * Created by Miguel Angel Aguilar Cuevas
 * 09/01/2019 at 2:56 PM
 */
package pages.newPages.memberPortal.myAccount;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyMembershipPage {
    public MyMembershipPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CLASS_NAME, using ="h2")
    private WebElement lblUserName;

    public WebElement getLblUserName() {
        return lblUserName;
    }
}

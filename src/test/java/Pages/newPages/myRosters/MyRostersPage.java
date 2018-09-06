/*
 * Created by Miguel Angel Aguilar Cuevas
 * 29/08/2018 at 12:21 PM
 */
package pages.newPages.myRosters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyRostersPage {

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnCreateRoster")
    private WebElement btnCreateRoster;

    public WebElement getBtnCreateRoster() {
        return btnCreateRoster;
    }

    public MyRostersPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

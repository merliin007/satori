/*
 * Created by Miguel Angel Aguilar Cuevas
 * 29/08/2018 at 12:23 PM
 */
package pages.newPages.myRosters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class NewRosterPage {

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_ddlLeagueID")
    private WebElement ddlSelectLeague;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnStartWizard")
    private WebElement btnAcknowledgeGetStarted;

    public WebElement getDdlSelectLeague() {
        return ddlSelectLeague;
    }

    public WebElement getBtnAcknowledgeGetStarted() {
        return btnAcknowledgeGetStarted;
    }

    public NewRosterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

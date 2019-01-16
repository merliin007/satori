/*
 * Created by Miguel Angel Aguilar Cuevas
 * 16/01/2019 at 12:53 PM
 */
package pages.memberPortal.myAccount;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MembershipsPaymentsPage {

    public MembershipsPaymentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_profileMembership_hlUpgradeCurrentMembership")
    private WebElement btnCurrentYearMembership;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_profileMembership_hlUpgradeNextMembership")
    private WebElement btnNextYearMembership;

    public WebElement getBtnCurrentYearMembership() {
        return btnCurrentYearMembership;
    }

    public WebElement getBtnNextYearMembership() {
        return btnNextYearMembership;
    }

    public WebElement getBtnByYear(String period) {
        switch (period.toLowerCase()) {
            case "current":
                return getBtnCurrentYearMembership();
            case "next":
                return getBtnNextYearMembership();
            default:
                return null;
        }
    }
}

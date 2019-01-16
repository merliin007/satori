/*
 * Created by Miguel Angel Aguilar Cuevas
 * 09/01/2019 at 2:56 PM
 */
package pages.memberPortal.myAccount;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MyMembershipPage {
    public MyMembershipPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CLASS_NAME, using = "h2")
    private WebElement lblUserName;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_MembershipPurchase1_rblCurrentYearMembership")
    private WebElement tblCurrentYear;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_MembershipPurchase1_rblNextYearMembership")
    private WebElement tblNextYear;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_MembershipPurchase1_btnNext")
    private WebElement btnNext;

    public WebElement getLblUserName() {
        return lblUserName;
    }

    public WebElement getTblCurrentYear() {
        return tblCurrentYear;
    }

    public WebElement getBtnNext() {
        return btnNext;
    }

    public WebElement getMembershipYearCheck(String year) {
        switch (year) {
            case "current":
                return tblCurrentYear.findElements(By.tagName("tr")).get(0).findElement(By.tagName("td")).findElement(By.tagName("label"));
            case "next":
                return tblNextYear.findElements(By.tagName("tr")).get(0).findElement(By.tagName("td")).findElement(By.tagName("label"));
            default:
                return null;
        }
    }
}

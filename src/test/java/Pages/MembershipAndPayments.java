package Pages;

import common.PaymentInfo.PaymentLoggedInfo;
import cucumber.api.java.de.Wenn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Date;

public class MembershipAndPayments {

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphMemberDashboard_profileMembership_hlAddPayment")
    private WebElement lnkAddMemberPayment;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphMemberDashboard_profileMembership_profilePaymentList_grdPayments")
    private WebElement tblPayments;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphMemberDashboard_lblBanner")
    private WebElement lblBanner;

    public MembershipAndPayments(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void ClickLinkOnMembershipPayments(String lnkName) {
        switch (lnkName.toLowerCase()) {
            case "add member payment":
                lnkAddMemberPayment.click();
                break;
            default:
                System.out.println("No option found");
        }
    }

    public PaymentLoggedInfo getCapturedPayment() {
        ArrayList<WebElement> tableRow = new ArrayList<>(tblPayments.findElements(By.tagName("tr")));
        ArrayList<WebElement> cells = new ArrayList<>(tableRow.get(1).findElements(By.tagName("td")));
        return (new PaymentLoggedInfo(cells));
    }

    public WebElement getTblPayments(){
        return tblPayments;
    }

    public WebElement getMembershipBanner(){
        return lblBanner;
    }

}

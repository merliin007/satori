package pages.oldPages;

import utility.Log;
import utility.paymentInfo.PaymentLoggedInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class MembershipAndPayments {

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphMemberDashboard_profileMembership_hlAddPayment")
    private WebElement lnkAddMemberPayment;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphMemberDashboard_profileMembership_profilePaymentList_grdPayments")
    private WebElement tblPayments;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphMemberDashboard_lblBanner")
    private WebElement lblBanner;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphMemberDashboard_profileMembership_btnSearch")
    private WebElement btnSearch;

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

    public ArrayList<PaymentLoggedInfo> getCapturedPayment() {
        ArrayList<WebElement> tableRow = new ArrayList<>(tblPayments.findElements(By.tagName("tr")));
        ArrayList<PaymentLoggedInfo> paymentList = new ArrayList<>();

        for(WebElement row: tableRow.subList(1,tableRow.size())){
            ArrayList<WebElement> cells = new ArrayList<>(row.findElements(By.tagName("td")));
            try {
                paymentList.add(new PaymentLoggedInfo(cells));
            } catch (DateTimeParseException e) {
                Log.warn(e.getMessage());
            }
        }
        return paymentList;
    }

    public WebElement getTblPayments(){
        return tblPayments;
    }

    public WebElement getMembershipBanner(){
        return lblBanner;
    }

    public WebElement getBtnSearch() { return btnSearch; }
}

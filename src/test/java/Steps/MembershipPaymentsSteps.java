/*
 * Created by Miguel Angel Aguilar Cuevas
 * 16/01/2019 at 3:14 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import pages.memberPortal.myAccount.MembershipsPaymentsPage;
import pages.memberPortal.myAccount.MyMembershipPage;
import pages.memberPortal.payments.PendingPaymentsPage;
import utility.Helpers;
import utility.Log;
import utility.paymentInfo.CreditCardInfo;

import static org.testng.Assert.fail;

public class MembershipPaymentsSteps {
    private BaseUtil base;
    private Helpers I;
    private MembershipsPaymentsPage membershipsPaymentsPage;

    public MembershipPaymentsSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
    }

    @And("^I pay his \"([^\"]*)\" membership using my credit card$")
    public void iPayHisMembershipUsingMyCreditCard(String period) {
        try{
            Log.info("Membership payment started");
            membershipsPaymentsPage = new MembershipsPaymentsPage(base.driver);
            I.Click(membershipsPaymentsPage.getBtnByYear(period));

            MyMembershipPage myMembership = new MyMembershipPage(base.driver);
            I.CheckCheckBox(myMembership.getMembershipYearCheck(period));
            I.Click(myMembership.getBtnNext());

            PendingPaymentsPage paymentsPage = new PendingPaymentsPage(base.driver);
            paymentsPage.setCreditCardInformationAndSubmit(new CreditCardInfo());
        }catch (Exception e){
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }
}

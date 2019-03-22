/*
 * Created by Miguel Angel Aguilar Cuevas
 * 16/01/2019 at 3:14 PM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.memberPortal.myAccount.MembershipsPaymentsPage;
import pages.memberPortal.myAccount.MyMembershipPage;
import pages.memberPortal.payments.PendingPaymentsPage;
import pages.memberPortal.payments.PurchaseDetailPage;
import pages.memberPortal.refunds.RefundPurchasePage;
import utility.Helpers;
import utility.Log;
import utility.payment.CreditCardInfo;
import utility.payment.Refund;
import utility.payment.PurchaseDetail;
import java.util.List;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class MembershipPaymentsSteps {
    private BaseUtil base;
    private Helpers I;
    private MembershipsPaymentsPage membershipsPaymentsPage;

    public MembershipPaymentsSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
        membershipsPaymentsPage = new MembershipsPaymentsPage(base.driver);
    }

    @And("^I pay his \"([^\"]*)\" membership using my credit card$")
    public void iPayHisMembershipUsingMyCreditCard(String period) {
        try {
            Log.info("Membership payment started");
            I.Click(membershipsPaymentsPage.getBtnByYear(period));

            MyMembershipPage myMembership = new MyMembershipPage(base.driver);
            I.CheckCheckBox(myMembership.getMembershipYearCheck(period));
            I.Click(myMembership.getBtnNext());

            PendingPaymentsPage paymentsPage = new PendingPaymentsPage(base.driver);
            paymentsPage.setCreditCardInformationAndSubmit(new CreditCardInfo());
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @When("^I ask for refund of purchase$")
    public void iAskForRefundOfPurchase(DataTable table) {
        try {
            Log.info("Asking for refund");
            I.selectOptionFromCell(1, "Refund", membershipsPaymentsPage);
            List<List<String>> rows = table.raw();

            Refund refund = new Refund(rows.get(1));
            RefundPurchasePage refundPurchasePage = new RefundPurchasePage(base.driver);

            I.SelectValue(refundPurchasePage.getDdlRefundMethod(), refund.getRefundMethod());
            I.Write(refundPurchasePage.getTxtDate(), refund.getDate());
            I.Write(refundPurchasePage.getTxtComments(), refund.getCommentsWithTime());
            I.Write(refundPurchasePage.getTxtAmount(), refund.getAmount());

            I.Click(refundPurchasePage.getBtnSave());

        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @When("^I delete purchase$")
    public void iDeletePurchase() {
        try {
            Log.info("Deleting purchase");
            I.selectOptionFromCell(1, "Delete", membershipsPaymentsPage);
            I.waitUntilElementIsVisible(membershipsPaymentsPage.getDeleteModal());
            I.Click(membershipsPaymentsPage.deleteModalElements("delete"));
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @Then("^I get \"([^\"]*)\" message$")
    public void iGetMessage(String message) {
        try {
            I.waitUntilExistenceOfElement(membershipsPaymentsPage.getToastrBy());
            assertTrue(membershipsPaymentsPage.getToastrMesage().contains(message));
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    @When("^I edit payment changing the following information$")
    public void iEditPaymentChangingTheFollowingInformation(DataTable table) {
        try {
            List<PurchaseDetail> productDetailList = table.asList(PurchaseDetail.class);

            Log.info("Purchase type");
            I.selectOptionFromCell(1, "Select", membershipsPaymentsPage);

            PurchaseDetailPage detailPage = new PurchaseDetailPage(base.driver);

            for (PurchaseDetail product : productDetailList) {
                I.Click(detailPage.getBtnChange());
                I.SelectValue(detailPage.getDdlProductType(), product.getPurchaseType());

                switch (product.getPurchaseType()) {
                    case "Membership":
                        I.waitUntilElementIsVisible(detailPage.getDdlMembership());
                        I.SelectValue(detailPage.getDdlMembership(), product.getMembership());
                        I.CheckCheckBoxIf(detailPage.getChkUpgrade(), product.getUpgrade());
                        I.SelectValue(detailPage.getDdlYear(), product.getYear());
                        break;
                    case "Ladder":
                        I.waitUntilElementIsVisible(detailPage.getDdlLadder());
                        I.SelectValue(detailPage.getDdlLadderYear(), product.getYear());
                        I.SelectValue(detailPage.getDdlLadder(), product.getLadder());
                        break;
                    case "Tournament":
                        I.waitUntilElementIsVisible(detailPage.getDdlTournament());
                        I.SelectValue(detailPage.getDdlTournamentYear(), product.getYear());
                        I.SelectValue(detailPage.getDdlTournament(), product.getTournament());
                        break;
                    case "Late Roster Fee":
                        I.waitUntilElementIsVisible(detailPage.getDdlLateRosterYear());
                        I.SelectValue(detailPage.getDdlLateRosterYear(), product.getYear());
                        break;
                        default: throw new IllegalArgumentException("Incorrect values entered for product change");
                }
                I.Write(detailPage.getTxtAmount(), product.getPurchaseAmount());
                I.Write(detailPage.getTxtComments(), product.getComments());
            }
            I.Click(detailPage.getBtnSave());

        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }
}

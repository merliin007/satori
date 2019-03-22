package steps;

import base.BaseUtil;
import org.openqa.selenium.UnhandledAlertException;
import pages.DefunctOldPages.*;
import pages.memberPortal.payments.PaymentConfirmationPage;
import utility.Helpers;
import utility.Log;
import utility.payment.BankAccountInfo;
import utility.payment.CheckInfo;
import utility.payment.CreditCardInfo;
import utility.payment.PaymentLoggedInfo;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.StaleElementReferenceException;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class PaymentsStep extends BaseUtil {
    private BaseUtil base;
    private MembershipAndPayments membershipAndPayments;
    private AddPaymentPage addPaymentPage;
    private PaymentsErrorPage paymentsErrorPage;
    private PaymentDetailsPage paymentDetailsPage;
    private RefundPage refundPage;
    private LocalDateTime purchaseTime;
    private String _paymentType;
    private Helpers I;

    public PaymentsStep(BaseUtil base) {
        this.base = base;
        I = new Helpers(base);
    }


   /* @Then("^I Should be logged in successfully$")
    public void iShouldBeLoggedInSuccessfully() {
        try {
            assertTrue(landingPage.IsUserLoggedIn());
            Log.info("User logged in successfully");
        } catch (Exception e) {
            fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @And("^Click on MemberShip & Payments$")
    public void clickOnMemberShipPayments() {
        try {
            myProfilePage.ClickLinkOnMyProfile("membership & payments");
            membershipAndPayments = new MembershipAndPayments(base.driver);
            Log.info("Going to Membership & payments");
        } catch (Exception e) {
            fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }*/

    @And("^Click on Add Member Payment$")
    public void clickOnAddMemberPayment() {
        try {
            membershipAndPayments.ClickLinkOnMembershipPayments("Add member payment");
            addPaymentPage = new AddPaymentPage(base.driver);
            Log.info("Going to Add member payment");
        } catch (Exception e) {
            fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @And("^Add the payment for the following member: \"([^\"]*)\"$")
    public void addThePaymentForTheFollowingUser(String altaNumber) {
        try {
            this.savePurchaseTime(addPaymentPage.getTxtDate().getAttribute("value"), addPaymentPage.getTxtTime().getAttribute("value"));
            addPaymentPage.setTxtAltaNumber(altaNumber);
            addPaymentPage.getBtnAdd().click();
            I.waitUntilElementIsVisible(addPaymentPage.getTblPurchases());
            Log.info("Entering alta number: " + altaNumber);
        } catch (Exception e) {
            fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @And("^After adding my credit card information I submit payment")
    public void afterAddingMyCreditCardInformationISubmitPayment() {
        try {
            _paymentType = "Credit Card";
            //this.savePurchaseTime(addPaymentPage.getTxtDate().getAttribute("value"), addPaymentPage.getTxtTime().getAttribute("value"));
            addPaymentPage.setDrpPaymentMethod(_paymentType);
            Log.info("Selecting " + _paymentType + " payment method");
            addPaymentPage.setCreditCardInformationAndSubmit(new CreditCardInfo());
            I.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
            Log.info("Payment submitted successfully");
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            paymentsErrorPage = new PaymentsErrorPage(base.driver);
            fail();
        }
    }

    @Then("^I get confirmation page Accepting my payment$")
    public void iGetConfirmationPageAcceptingMyPayment() {
        assertNull(paymentsErrorPage);
        PaymentLoggedInfo payment = null;
        boolean result = true;
        try {
            I.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
            payment = membershipAndPayments.getCapturedPayment().get(0);
            result = (payment.getStatus().equals("Captured") && _paymentType.equals(payment.getPaymentMethod()) &&
                    payment.getPaymentDate().equals(purchaseTime));
            /*commonActions.IsLocalDateTimeAroundServerDate(actualTime, payment.getPaymentDate(), 5L));*/
            assertTrue(result);
            Log.info("Payment is registered");
        } catch (Exception e) {
            fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        } finally {
            if (payment != null && result) {
                deletePayment(payment);
            }
        }
    }

    @And("^After adding my Bank Account information I submit all information$")
    public void afterAddingMyBankAccountInformationISubmitAllInformation() {
        try {
            _paymentType = "Bank Account";
            addPaymentPage.setDrpPaymentMethod(_paymentType);
            Log.info("Selecting " + _paymentType + " payment method");

            addPaymentPage.setBankAccountInformationAndSubmit(new BankAccountInfo());
            I.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
            Log.info("Payment submitted successfully");
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            paymentsErrorPage = new PaymentsErrorPage(base.driver);
            fail();
        }
    }

    @And("^After selecting Office Credit option I submit all information$")
    public void afterSelectingOfficeCreditOptionISubmitAllInformation() {
        try {
            _paymentType = "Office Credit";
            //this.savePurchaseTime(addPaymentPage.getTxtDate().getText(), addPaymentPage.getTxtTime().getText());
            addPaymentPage.setDrpPaymentMethod(_paymentType);
            Log.info("Selecting " + _paymentType + " payment method");
            addPaymentPage.clickNoCCSubmitBtn();
            I.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
            Log.info("Payment submitted successfully");
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            paymentsErrorPage = new PaymentsErrorPage(base.driver);
            fail();
        }
    }

    @And("^After selecting \"([^\"]*)\" option I submit all information$")
    public void afterSelectingOfficeCreditOptionISubmitAllInformation(String _paymentType) {
        try {
            addPaymentPage.setDrpPaymentMethod(_paymentType);
            Log.info("Selecting " + _paymentType + " payment method");
            addPaymentPage.clickNoCCSubmitBtn();
            I.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
            Log.info("Payment submitted successfully");
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            paymentsErrorPage = new PaymentsErrorPage(base.driver);
            fail();
        }
    }


    @When("^Manually adding my credit card information I submit all information$")
    public void manuallyAddingMyCreditCardInformationISubmitAllInformation(DataTable table) {
        try {
            _paymentType = "Credit Card";
            addPaymentPage.setDrpPaymentMethod(_paymentType);
            Log.info("Selecting " + _paymentType + " payment method");

            List<CreditCardInfo> ccDetail = new ArrayList<>();
            ccDetail = table.asList(CreditCardInfo.class);

            for (CreditCardInfo cc : ccDetail) {
                Log.info("Testing with: " + cc.getCcNumber());
                try {
                    addPaymentPage.setCreditCardInformationAndSubmit(cc);
                    I.verifyAlertErrorAndAcceptIt();
                    addPaymentPage.clearCreditCardFields();
                }catch (UnhandledAlertException e){
                    I.verifyAlertErrorAndAcceptIt();
                    addPaymentPage.clearCreditCardFields();
                }
                catch (Exception e) {
                    Log.error(e.getMessage());
                }
            }
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            paymentsErrorPage = new PaymentsErrorPage(base.driver);
            fail();
        }
    }

    @Then("^No payment was registered$")
    public void noPaymentWasRegistered() {
        try {
            assertEquals(base.driver.getTitle(), "ALTA Tennis - Add Payment");
            addPaymentPage.clickCancelButton();
            I.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
            ArrayList<PaymentLoggedInfo> payments = membershipAndPayments.getCapturedPayment();
            Log.info("Validating no payment was registered");
            for (PaymentLoggedInfo payment : payments) {
                assertFalse(I.IsLocalDateTimeAroundServerDate(LocalDateTime.now(), payment.getPaymentDate(), 2L));
            }
        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @When("^Upgrading membership for user \"([^\"]*)\"$")
    public void upgradingMembershipForUser(String user) {
        try {
            this.savePurchaseTime(addPaymentPage.getTxtDate().getAttribute("value"), addPaymentPage.getTxtTime().getAttribute("value"));
            addPaymentPage.setTxtAltaNumber(user);
            addPaymentPage.setChkUpgrade(true);
            addPaymentPage.getBtnAdd().click();
            I.waitUntilElementIsVisible(addPaymentPage.getTblPurchases());
            Log.info("Upgrading membership for member: " + user);
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            paymentsErrorPage = new PaymentsErrorPage(base.driver);
            fail();
        }
    }

    @And("^Purchase detail page shows correct payment$")
    public void purchaseDetailPageShowsCorrectPayment() {
        PaymentInfo paymentFound = null;
        try {
            paymentFound = findPayment();
            assertTrue(paymentFound.isResult());
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        } finally {
            if (paymentFound.isResult()) {
                I.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
                deletePayment(membershipAndPayments.getCapturedPayment().get(paymentFound.getI()));
            }
        }
    }

    private void savePurchaseTime(String date, String time) throws Exception {
        purchaseTime = LocalDateTime.parse(date + " " + time,
                DateTimeFormatter.ofPattern("M/dd/yyyy h:mm:ss a"));
    }

    private void deletePayment(PaymentLoggedInfo payment) {
        try {
            for (int i = 0; i < 5; i++) {
                try {
                    payment.getLnkDelete().click();
                    base.driver.switchTo().alert().accept();
                    base.driver.switchTo().defaultContent();
                    Log.info("Deleting Payment");
                    I.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
                    I.waitUntilElementIsClickable(membershipAndPayments.getBtnSearch());
                    break;
                } catch (StaleElementReferenceException e) {
                    Log.warn("Trying to recover delete link element, attempt: " + i);
                }
            }
        } catch (Exception e) {
            Log.info("Deleting Payment failed: " + e.getMessage());
        }
    }

    @And("^After adding my check information I submit payment$")
    public void afterAddingMyCheckInformationISubmitPayment() {
        try {
            _paymentType = "Check";
            addPaymentPage.setDrpPaymentMethod(_paymentType);
            Log.info("Selecting " + _paymentType + " payment method");
            addPaymentPage.setCheckInformation(new CheckInfo());
            addPaymentPage.clickNoCCSubmitBtn();
            I.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
            Log.info("Payment submitted successfully");
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            paymentsErrorPage = new PaymentsErrorPage(base.driver);
            fail();
        }
    }

    @Then("^Refund is made for this payment$")
    public void refundIsMadeForThisPayment() {
        try {
            PaymentInfo paymentFound = findPayment();
            assertTrue(paymentFound.isResult());
            I.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
            refundPayment(membershipAndPayments.getCapturedPayment().get(paymentFound.getI()));

        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    private void refundPayment(PaymentLoggedInfo paymentLoggedInfo) {
        try {
            Log.info("Refunding payment");
            paymentLoggedInfo.getLnkRefund().click();
            refundPage = new RefundPage(base.driver);
            I.waitUntilElementIsVisible(refundPage.getDdlRefundMethod());
            refundPage.setComments("Refunding payment per Automator");
            refundPage.getBtnSave().click();
            I.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
    }

    private PaymentInfo findPayment() throws Exception {
        boolean result = false;
        ArrayList<PaymentLoggedInfo> payments = membershipAndPayments.getCapturedPayment();
        int i = 0;
        for (PaymentLoggedInfo payment : payments) {
            Log.info("Reviewing payment: " + payment.getPaymentId());
            if (payment.getPaymentDate().equals(purchaseTime)) {
                payment.getLnkSelect().click();
                paymentDetailsPage = new PaymentDetailsPage(base.driver);
                I.waitUntilElementIsVisible(paymentDetailsPage.getTab(1));
                String _detailAmount = "$" + paymentDetailsPage.getTxtAmount().getAttribute("value");
                String _detailMethod = paymentDetailsPage.getLblMethod().getText();
                if (_detailAmount.equals(payment.getAmount()) && _detailMethod.equals(payment.getPaymentMethod())) {
                    result = true;
                    break;
                } else
                    paymentDetailsPage.clickCancel();
            }
            i++;
        }
        paymentDetailsPage.clickCancel();
        return new PaymentInfo(i, result);
    }

    private class PaymentInfo {

        private int i;
        private boolean result;
        public int getI() {
            return i;
        }

        public boolean isResult() {
            return result;
        }

        public PaymentInfo(int i, boolean result) {
            this.i = i;
            this.result = result;
        }

    }

    @Then("^I get success payment confirmation$")
    public void iGetSuccessPaymentConfirmation(){
        try{
            PaymentConfirmationPage confirmation = new PaymentConfirmationPage(base.driver);
            I.waitUntilElementIsVisible(confirmation.getLblConfirmation());
            Log.info("Payment confirmation: " + confirmation.getLblConfirmation().getText() );
            I.Click(confirmation.getBtnContinue());
        }catch (Exception e){
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }

    }
}

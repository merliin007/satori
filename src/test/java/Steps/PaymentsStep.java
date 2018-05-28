package steps;

import base.BaseUtil;
import org.testng.annotations.Parameters;
import utility.Log;
import pages.*;
import utility.paymentInfo.BankAccountInfo;
import utility.paymentInfo.CheckInfo;
import utility.paymentInfo.CreditCardInfo;
import common.CommonActions;
import utility.paymentInfo.PaymentLoggedInfo;
import utility.credentials.MemberCredentials;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
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
    private CommonActions commonActions;
    private LoginPage loginPage;
    private MainPage mainPage;
    private MyProfilePage myProfilePage;
    private MembershipAndPayments membershipAndPayments;
    private AddPaymentPage addPaymentPage;
    private PaymentsErrorPage paymentsErrorPage;
    private PaymentDetailsPage paymentDetailsPage;
    private RefundPage refundPage;
    private LocalDateTime purchaseTime;
    private String _paymentType;

    public PaymentsStep(BaseUtil base) {
        this.base = base;
    }

    @Given("^I sign in as Default user$")
    public void iSignInAsDefaultUser() {
        try {
            mainPage = new MainPage(base.driver);
            MemberCredentials memberCredentials = new MemberCredentials();
            mainPage.LoginWith(memberCredentials);
            commonActions = new CommonActions(base.driver);
            Log.info("Logging as: " + memberCredentials.getUsername());
        } catch (Exception e) {
            fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @Given("^I sign in as member user$")
    public void iSignInAsMemberUser(DataTable table) {
        try {
            mainPage = new MainPage(base.driver);
            List<MemberCredentials> memberCredentials = new ArrayList<>();
            memberCredentials = table.asList(MemberCredentials.class);
            mainPage.LoginWith(memberCredentials.get(0));
            commonActions = new CommonActions(base.driver);
            Log.info("Logging as: " + memberCredentials.get(0).getUsername());
        } catch (Exception e) {
            fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @Then("^I Should be logged in successfully$")
    public void iShouldBeLoggedInSuccessfully() {
        try {
            assertTrue(mainPage.IsUserLoggedIn());
            Log.info("User logged in successfully");
        } catch (Exception e) {
            fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @And("^I navigate to My Profile menu$")
    public void iNavigateToMyProfileMenu() {
        try {
            mainPage.GoToMyProfile();
            myProfilePage = new MyProfilePage(base.driver);
            Log.info("Going to My Profile");
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
    }

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
            addPaymentPage.setTxtAltaNumber(altaNumber);
            addPaymentPage.getBtnAdd().click();
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
            this.savePurchaseTime(addPaymentPage.getTxtDate().getAttribute("value"), addPaymentPage.getTxtTime().getAttribute("value"));
            addPaymentPage.setDrpPaymentMethod(_paymentType);
            Log.info("Selecting " + _paymentType + " payment method");
            addPaymentPage.setCreditCardInformationAndSubmit(new CreditCardInfo());
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
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
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
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
            this.savePurchaseTime(addPaymentPage.getTxtDate().getText(), addPaymentPage.getTxtTime().getText());
            addPaymentPage.setBankAccountInformationAndSubmit(new BankAccountInfo());
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
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
            this.savePurchaseTime(addPaymentPage.getTxtDate().getText(), addPaymentPage.getTxtTime().getText());
            addPaymentPage.setDrpPaymentMethod(_paymentType);
            Log.info("Selecting " + _paymentType + " payment method");
            addPaymentPage.clickNoCCSubmitBtn();
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
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
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
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
                    commonActions.verifyAlertErrorAndAcceptIt();
                    addPaymentPage.clearCreditCardFields();
                } catch (Exception e) {
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
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
            ArrayList<PaymentLoggedInfo> payments = membershipAndPayments.getCapturedPayment();
            Log.info("Validating no payment was registered");
            for (PaymentLoggedInfo payment : payments) {
                assertFalse(commonActions.IsLocalDateTimeAroundServerDate(LocalDateTime.now(), payment.getPaymentDate(), 2L));
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
                commonActions.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
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
                    commonActions.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
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
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
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
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
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
            commonActions.waitUntilElementIsVisible(refundPage.getDdlRefundMethod());
            refundPage.setComments("Refunding payment per Automator");
            refundPage.getBtnSave().click();
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
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
                commonActions.waitUntilElementIsVisible(paymentDetailsPage.getTab(1));
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
}

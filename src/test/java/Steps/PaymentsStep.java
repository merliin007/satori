package Steps;

import Base.BaseUtil;
import Utility.Log;
import Pages.*;
import Utility.PaymentInfo.BankAccountInfo;
import Utility.PaymentInfo.CreditCardInfo;
import common.CommonActions;
import Utility.PaymentInfo.PaymentLoggedInfo;
import Utility.credentials.MemberCredentials;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
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

    //private LocalDateTime actualTime;
    private LocalDateTime purchaseTime;
    private String _paymentType;

    public PaymentsStep(BaseUtil base) {
        this.base = base;
    }

    @Given("^I sign in as \"([^\"]*)\" user$")
    public void iSignInAsUserInEnvironment(String user) {
        try {
            base.NavigateToPage("test");
            mainPage = new MainPage(base.driver);
            mainPage.LoginWith(new MemberCredentials());
            commonActions = new CommonActions(base.driver);
            Log.info("Logging as: " + user);
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
            addPaymentPage.setDrpPaymentMethod(_paymentType);
            Log.info("Selecting " + _paymentType + " payment method");
            this.savePurchaseTime(addPaymentPage.getTxtDate().getAttribute("value"), addPaymentPage.getTxtTime().getAttribute("value"));
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
            addPaymentPage.setDrpPaymentMethod(_paymentType);
            Log.info("Selecting " + _paymentType + " payment method");
            this.savePurchaseTime(addPaymentPage.getTxtDate().getText(), addPaymentPage.getTxtTime().getText());
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
        boolean paymentFound = false;
        ArrayList<PaymentLoggedInfo> payments = null;
        int i = 0;
        try {
            payments = membershipAndPayments.getCapturedPayment();
            for (PaymentLoggedInfo payment : payments) {
                Log.info("Reviewing payment: " + payment.getPaymentId());
                if (payment.getPaymentDate().equals(purchaseTime)) {
                    payment.getLnkSelect().click();
                    paymentDetailsPage = new PaymentDetailsPage(base.driver);
                    commonActions.waitUntilElementIsVisible(paymentDetailsPage.getTab(1));
                    String _detailAmount = "$" + paymentDetailsPage.getTxtAmount().getAttribute("value");
                    String _detailMethod = paymentDetailsPage.getLblMethod().getText();
                    if (_detailAmount.equals(payment.getAmount()) && _detailMethod.equals(payment.getPaymentMethod())) {
                        paymentFound = true;
                        break;
                    } else
                        paymentDetailsPage.clickCancel();
                }
                i++;
            }
            paymentDetailsPage.clickCancel();

            assertTrue(paymentFound);
        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }
        finally {
            if(paymentFound && payments.size() > 0){
                commonActions.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
                //commonActions.waitUntilElementIsAvailableAfterRefresh(payments.get(i).getLnkDelete());
                //deletePayment(membershipAndPayments.getCapturedPayment().get(i));
                deletePayment(payments.get(i));
            }
        }
    }

    private void savePurchaseTime(String date, String time) throws Exception{
        purchaseTime = LocalDateTime.parse(date + " " + time,
                DateTimeFormatter.ofPattern("M/dd/yyyy h:mm:ss a"));
    }
    private void deletePayment(PaymentLoggedInfo payment){
        try {
            for(int i = 0; i < 5; i++){
                try {
                    payment.getLnkDelete().click();
                    base.driver.switchTo().alert().accept();
                    base.driver.switchTo().defaultContent();
                    Log.info("Deleting Payment");
                    commonActions.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
                } catch (StaleElementReferenceException e) {
                    Log.warn("Trying to recover delete link element, attempt: " + i);
                }
            }
        } catch (Exception e) {
            Log.info("Deleting Payment failed: " + e.getMessage());
        }
    }
}

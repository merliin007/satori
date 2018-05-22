package Steps;

import Base.BaseUtil;
import Utility.Log;
import Pages.*;
import Utility.PaymentInfo.BankAccountInfo;
import Utility.PaymentInfo.CreditCardInfo;
import common.CommonActions;
import Utility.PaymentInfo.PaymentLoggedInfo;
import Utility.credentials.MemberCredentials;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;
import java.time.LocalDateTime;



public class PaymentsStep extends BaseUtil{
    private BaseUtil base;
    private CommonActions commonActions;
    private LoginPage loginPage;
    private MainPage mainPage;
    private MyProfilePage myProfilePage;
    private MembershipAndPayments membershipAndPayments;
    private AddPaymentPage addPaymentPage;
    private PaymentsErrorPage paymentsErrorPage;

    private LocalDateTime actualTime;
    private String _paymentType;

    public PaymentsStep(BaseUtil base) {
        this.base = base;
    }

    @Given("^I sign in as \"([^\"]*)\" user$")
    public void iSignInAsUserInEnvironment(String user){
        try {
            base.NavigateToPage("test");
            mainPage = new MainPage(base.driver);
            mainPage.LoginWith(new MemberCredentials());
            commonActions = new CommonActions(base.driver);
            Log.info("Logging as: " + user);
        } catch (Exception e) {
            Assert.fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @Then("^I Should be logged in successfully$")
    public void iShouldBeLoggedInSuccessfully(){
        try {
            Assert.assertTrue(mainPage.IsUserLoggedIn());
            Log.info("User logged in successfully");
        } catch (Exception e) {
            Assert.fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @And("^I navigate to My Profile menu$")
    public void iNavigateToMyProfileMenu(){
        try {
            mainPage.GoToMyProfile();
            myProfilePage = new MyProfilePage(base.driver);
            Log.info("Going to My Profile");
        } catch (Exception e) {
            Assert.fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @And("^Click on MemberShip & Payments$")
    public void clickOnMemberShipPayments(){
        try {
            myProfilePage.ClickLinkOnMyProfile("membership & payments");
            membershipAndPayments = new MembershipAndPayments(base.driver);
            Log.info("Going to Membership & payments");
        } catch (Exception e) {
            Assert.fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @And("^Click on Add Member Payment$")
    public void clickOnAddMemberPayment(){
        try {
            membershipAndPayments.ClickLinkOnMembershipPayments("Add member payment");
            addPaymentPage = new AddPaymentPage(base.driver);
            Log.info("Going to Add member payment");
        } catch (Exception e) {
            Assert.fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @And("^Add the payment for the following member: \"([^\"]*)\"$")
    public void addThePaymentForTheFollowingUser(String altaNumber){
        try {
            addPaymentPage.setTxtAltaNumber(altaNumber);
            addPaymentPage.getBtnAdd().click();
            Log.info("Entering alta number: " + altaNumber );
        } catch (Exception e) {
            Assert.fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @And("^After adding my credit card information I submit all information$")
    public void afterAddingMyCreditCardInformationISubmitAllInformation() {
        try {
            _paymentType = "Credit Card";
            addPaymentPage.setDrpPaymentMethod(_paymentType);
            Log.info("Selecting Credit Card payment method");
            addPaymentPage.setCreditCardInformationAndSubmit( new CreditCardInfo());
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
            actualTime = LocalDateTime.now();
            Log.info("Payment submitted successfully");
        }
        catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            paymentsErrorPage = new PaymentsErrorPage(base.driver);
            Assert.fail();
        }
    }

    @Then("^I get confirmation page Accepting my payment$")
    public void iGetConfirmationPageAcceptingMyPayment(){
        Assert.assertNull(paymentsErrorPage);
        PaymentLoggedInfo payment = null;
        boolean result = true;
        try {
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
            payment = membershipAndPayments.getCapturedPayment();
            result = (payment.getStatus().equals("Captured") && _paymentType.equals(payment.getPaymentMethod()) &&
                    commonActions.IsLocalDateTimeAroundServerDate(actualTime, payment.getPaymentDate(), 5L));
            Assert.assertTrue(result);
            Log.info("Payment is registered");
        } catch (Exception e) {
            Assert.fail();
            Log.error(e.getMessage());
            base.GrabScreenShot();
        }
        finally {
            if(payment != null && result){
                payment.getLnkDelete().click();
                base.driver.switchTo().alert().accept();
                base.driver.switchTo().defaultContent();
                Log.info("Deleting Payment");
                commonActions.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());

            }
        }
    }

    @And("^After adding my Bank Account information I submit all information$")
    public void afterAddingMyBankAccountInformationISubmitAllInformation(){
        try {
            _paymentType = "Bank Account";
            addPaymentPage.setDrpPaymentMethod(_paymentType);
            Log.info("Selecting Bank Account payment method");
            addPaymentPage.setBankAccountInformationAndSubmit( new BankAccountInfo());
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
            actualTime = LocalDateTime.now();
            Log.info("Payment submitted successfully");
        }
        catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            paymentsErrorPage = new PaymentsErrorPage(base.driver);
            Assert.fail();
        }
    }
}

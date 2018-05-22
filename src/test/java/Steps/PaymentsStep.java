package Steps;

import Base.BaseUtil;
import Pages.*;
import common.CommonActions;
import common.PaymentInfo.PaymentLoggedInfo;
import common.credentials.MemberCredentials;
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

    public PaymentsStep(BaseUtil base) {
        this.base = base;
    }

    @Given("^I sign in as \"([^\"]*)\" user in \"([^\"]*)\" environment$")
    public void iSignInAsUserInEnvironment(String user, String environment) throws InterruptedException{
        base.NavigateToPage(environment);
        loginPage = new LoginPage(base.driver);
        loginPage.LoginWith(new MemberCredentials());
        commonActions = new CommonActions(base.driver);
    }

    @And("^I navigate to My Profile menu$")
    public void iNavigateToMyProfileMenu() throws InterruptedException{
        mainPage.GoToMyProfile();
        myProfilePage = new MyProfilePage(base.driver);
    }

    @And("^Click on MemberShip & Payments$")
    public void clickOnMemberShipPayments(){
        myProfilePage.ClickLinkOnMyProfile("membership & payments");
        membershipAndPayments = new MembershipAndPayments(base.driver);
    }

    @And("^Click on Add Member Payment$")
    public void clickOnAddMemberPayment(){
        membershipAndPayments.ClickLinkOnMembershipPayments("Add member payment");
        addPaymentPage = new AddPaymentPage(base.driver);
    }

    @And("^Add the payment for the following member: \"([^\"]*)\"$")
    public void addThePaymentForTheFollowingUser(String altaNumber){
        addPaymentPage.setTxtAltaNumber(altaNumber);
        addPaymentPage.getBtnAdd().click();
    }

    @And("^After adding my credit card information I submit all information$")
    public void afterAddingMyCreditCardInformationISubmitAllInformation() {
        try {
            addPaymentPage.setDrpPaymentMethod("Credit Card");
            addPaymentPage.setCreditCardInformationAndSubmit("4111111111111111", "Miguel Aguilar", "11",
                    "2020", "159");
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getMembershipBanner());
            actualTime = LocalDateTime.now();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            base.GrabScreenShot();
            paymentsErrorPage = new PaymentsErrorPage(base.driver);
            Assert.fail();
        }
    }

    @Then("^I get confirmation page Accepting my payment$")
    public void iGetConfirmationPageAcceptingMyPayment(){
        Assert.assertNull(paymentsErrorPage);
        try {
            commonActions.waitUntilElementIsVisible(membershipAndPayments.getTblPayments());
            PaymentLoggedInfo payment = membershipAndPayments.getCapturedPayment();
            Assert.assertEquals("Captured", payment.getStatus());
            Assert.assertEquals("Credit Card", payment.getPaymentMethod());
            Assert.assertTrue(commonActions.IsLocalDateTimeAroundServerDate(actualTime, payment.getPaymentDate(),5L));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            base.GrabScreenShot();
        }
    }

    @Then("^I Should be logged in successfully$")
    public void iShouldBeLoggedInSuccessfully() throws InterruptedException {
        mainPage = new MainPage(base.driver);
        Assert.assertTrue(mainPage.IsUserLoggedIn());
    }
}

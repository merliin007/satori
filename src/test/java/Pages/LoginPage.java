package pages;

import utility.credentials.MemberCredentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_txtUserName")
    private WebElement txtUsername;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_txtPassword")
    private WebElement txtpassword;

    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_cphM_cphM_cphApp_btnLogin")
    private WebElement btn_submit;

    @FindBy(how = How.ID, using = "txtPassword")
    private WebElement txtPassword;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void LoginWith(MemberCredentials member) {
        clearFields();
        txtUsername.sendKeys(member.getUsername());
        txtPassword.sendKeys(member.getPassword());
        btn_submit.click();
    }

    private void clearFields() {
        txtUsername.clear();
        txtPassword.clear();
    }



}

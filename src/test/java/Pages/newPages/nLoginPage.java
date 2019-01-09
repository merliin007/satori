package pages.newPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class nLoginPage {

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_txtUserName")
    private WebElement txtUsername;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_txtPassword")
    private WebElement txtPassword;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_btnSingin")
    private WebElement btnSignIn;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_HyperLink1")
    private WebElement lnkForgotUsername;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_lnkForgotPassword")
    private WebElement lnkForgotPassword;
    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_chkKeepLogged")
    private WebElement chkKeepLogged;
    @FindBy (how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_lblError")
    private WebElement lblError;
    @FindBy(how =How.ID, using ="ctl00_ctl00_CPHolder_CPHolder_HyperLink2")
    private WebElement linkSingup;

    public nLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getTxtUsername() {
        return txtUsername;
    }

    public WebElement getTxtPassword() {
        return txtPassword;
    }

    public WebElement getBtnSignIn() {
        return btnSignIn;
    }

    public WebElement getLnkForgotUsername() {
        return lnkForgotUsername;
    }

    public WebElement getLnkForgotPassword() {
        return lnkForgotPassword;
    }

    public WebElement getChkKeepLogged() {
        return chkKeepLogged;
    }

    public WebElement getLblError(){
        return lblError;
    }

    public WebElement getLinkSingup() {
        return linkSingup;
    }
}

package pages.newPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TrioLoginPage {
    @FindBy(how = How.ID, using = "login-email")
    private WebElement txtLogin;

    @FindBy(how = How.ID, using = "login-password")
    private WebElement txtPassword;

    @FindBy(how = How.ID, using = "login-btn")
    private WebElement btnLogin;

    @FindBy(how = How.CLASS_NAME, using = "loader-backdrop")
    private WebElement loader;

    public TrioLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getLoader() {
        return loader;
    }

    public void LoginWith(String username, String password){
        txtLogin.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }

    public WebElement getTxtLogin() {
        return txtLogin;
    }

    public WebElement getTxtPassword() {
        return txtPassword;
    }
}

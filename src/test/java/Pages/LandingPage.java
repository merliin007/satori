package pages;

import utility.credentials.MemberCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class LandingPage {
    private WebDriver _driver;
    private WebElement element;

    //@FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_LblWelcome")
    By byLblWelcome = By.id("ctl00_ctl00_ctl00_LblWelcome");

    @FindBy(how = How.ID, using = "membership")
    private WebElement mnuMyALTA;

    @FindBy(how = How.ID, using = "username")
    private WebElement username;

    @FindBy(how = How.ID, using = "password")
    private WebElement over_password;

    @FindBy(how = How.ID, using = "txtPassword")
    private WebElement txtPassword;

    @FindBy(how = How.ID, using = "signinRegister")
    private WebElement btnSignIn;

    public LandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    public void LoginWith(MemberCredentials credentials) throws Exception{
        username.clear();
        username.sendKeys(credentials.getUsername());
        txtPassword.click();
        over_password.sendKeys(credentials.getPassword());
        btnSignIn.submit();

    }

    public boolean IsUserLoggedIn() throws Exception{
        return _driver.findElement(byLblWelcome).isDisplayed();
    }

    public By getLblWelcomeBy() {
        return byLblWelcome;
    }

    public void GoToMyProfile() throws Exception{
        Actions actions = new Actions(_driver);
        ArrayList<WebElement> ele = new ArrayList<>(_driver.findElements(By.xpath("//*[@id=\"membership\"]/span")));
        actions.moveToElement(ele.get(1)).perform();
        _driver.findElement(By.xpath("//*[@id=\"multi-level\"]/ul/li[5]/ul/li[1]/a")).click();
    }

    public WebElement getBtnSignIn() {
        return btnSignIn;
    }
}

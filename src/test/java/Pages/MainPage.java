package Pages;

import common.credentials.MemberCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class MainPage {
    private WebDriver _driver;
    private WebElement element;
    @FindBy(how = How.ID, using = "ctl00_ctl00_ctl00_LblWelcome")
    private WebElement lblWelcome;

    @FindBy(how = How.ID, using = "membership")
    private WebElement mnuMyALTA;

    @FindBy(how = How.ID, using = "username")
    private WebElement username;

    @FindBy(how = How.ID, using = "password")
    private WebElement password;

    @FindBy(how = How.ID, using = "signin")
    private WebElement btnSignIn;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    public void LoginWith(MemberCredentials credentials){
        username.clear();
        username.sendKeys(credentials.getUsername());
        Actions actions = new Actions(_driver);
        actions.moveToElement(password).perform();
        actions.click(password);
        password.sendKeys(credentials.getPassword());

        btnSignIn.submit();

    }

    public boolean IsUserLoggedIn(){
        return !(lblWelcome == null);
    }

    public void GoToMyProfile() throws InterruptedException {
        /*_driver.findElement(By.partialLinkText("My Profile")).click();
        Thread.sleep(2000L);*/
        Actions actions = new Actions(_driver);
        ArrayList<WebElement> ele = new ArrayList<>(_driver.findElements(By.xpath("//*[@id=\"membership\"]/span")));
        actions.moveToElement(ele.get(1)).perform();
        _driver.findElement(By.xpath("//*[@id=\"multi-level\"]/ul/li[5]/ul/li[1]/a")).click();
    }


}

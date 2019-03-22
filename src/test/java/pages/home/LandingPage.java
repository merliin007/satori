package pages.home;

import utility.Log;
import utility.credentials.MemberCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LandingPage {
    private WebDriver _driver;
    private WebElement element;

    public LandingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;
    }

    By byLblWelcome = By.id("ctl00_ctl00_ctl00_LblWelcome");

    @FindBy(how = How.ID, using = "ctl00_signinRegister")
    private WebElement btnSignIn;

    @FindBy(how = How.ID, using = "navbarMainToggle")
    private WebElement btnNavigator;

    @FindBy(how = How.CLASS_NAME, using = "megamenu__list-col")
    private WebElement navList;

    @FindBy(how = How.CLASS_NAME, using ="breadcrumbs")
    private WebElement breadCrumbs;

    public boolean IsUserLoggedIn() throws Exception {
        return _driver.findElement(byLblWelcome).isDisplayed();
    }

    public By getLblWelcomeBy() {
        return byLblWelcome;
    }


    public WebElement getBtnSignIn() {
        return btnSignIn;
    }

    public WebElement getBtnNavigator() {
        return btnNavigator;
    }

    public WebElement getNavList() {
        return navList;
    }

        public WebElement getLinkOptionFromMenu(String lnkName) throws Exception {
        List<WebElement> elementList = navList.findElements(By.className("megamenu__section"));
        for (WebElement element : elementList) {
            List<WebElement> subList = element.findElements(By.tagName("a"));
            for (WebElement sub : subList) {
                if (sub.getText().trim().contains(lnkName.trim())) {
                    Log.info("URL found: " + sub.getAttribute("href"));
                    return sub;
                }
            }
        }
        return null;
    }

    public WebElement getBreadCrumbs() {
        return breadCrumbs;
    }

    public String getBreadCrumbChildText(){
        try {
            return breadCrumbs.findElement(By.className("breadcrumbs__text")).getText();
        } catch (Exception e) {
            return "Home";
        }
    }

    public By getBreadCrumbLocator(){
        return By.className("breadcrumbs__text");
    }
}

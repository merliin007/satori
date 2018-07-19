package pages.newPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class nMainPage {

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_LblWelcome")
    private WebElement lblWelcome;

    @FindBy(how = How.ID, using = "welcomeContainer")
    private WebElement lblContainer;

    @FindBy(how = How.ID, using = "navbar-content")
    private WebElement navigator;

    @FindBy(how = How.ID, using = "aspnetForm")
    private WebElement form;

    @FindBy(how = How.ID, using ="ctl00_ctl00_CPHolder_navRibbon1_lnkDashboard")
    private WebElement lnkDashboard;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_navRibbon1_lnkContent")
    private WebElement lnkContent;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_navRibbon1_lnkCalendars")
    private WebElement lnkCalendars;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_navRibbon1_HyperLink1")
    private WebElement lnkEvents;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_navRibbon1_lnkProducts")
    private WebElement lnkProducts;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_navRibbon1_lnkRules")
    private WebElement lnkRulesAdmin;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_navRibbon1_lnkLeagues")
    private WebElement lnkLeagues;

    /*@FindBy(how = How.ID, using = "")
    private WebElement lnk;*/


    public nMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getLblWelcome() {
        return lblWelcome;
    }

    public WebElement getLblContainer() {
        return lblContainer;
    }

    public WebElement getNavigator() throws Exception {
        return form.findElement(By.cssSelector("button.navbar-toggler"));
    }

    public WebElement getNavigatorCloseButton() throws Exception {
        return form.findElement(By.cssSelector("#navbar-content > button > span"));

    }

    public WebElement getNavigatorLink(String lnkName) throws Exception {
        switch (lnkName.toLowerCase()) {
            case "dashboard":
                return lnkDashboard;
            case "content editing":
                return lnkContent;
            case "calendars":
                return lnkCalendars;
            case "events":
                return lnkEvents;
            case "products":
                return lnkProducts;
            case "rules/admin":
                return lnkRulesAdmin;
            case "leagues":
                return lnkLeagues;
            default:
                return null;
        }
    }


}

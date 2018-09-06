package pages.newPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class nMainPage {

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_LblWelcome")
    private WebElement lblWelcome;

    @FindBy(how = How.ID, using = "welcomeContainer")
    private WebElement lblContainer;

    @FindBy(how = How.ID, using = "navbar-content")
    private WebElement navigator;

    @FindBy(how = How.ID, using = "aspnetForm")
    private WebElement form;
/*
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
    private WebElement lnkLeagues;*/

    /*@FindBy(how = How.ID, using = "")
    private WebElement lnk;*/
    @FindBy(how = How.ID, using = "scrollChild")
    private WebElement menuListOptions;

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

    private static final Map<String, Integer> MENU_SECTIONS = new HashMap<String, Integer>() {{
        // alta manage section
        put("website support", 1);
        put("league support", 2);
        put("member and rosters", 3);
        put("ladder and mixers", 4);
        put("reports", 5);
        put("service recognition", 6);

        put("my teams", 1);
        put("my performance", 2);
        put("my rosters", 3);
        put("my ladders & mixers", 4);
        put("my account", 5);
    }};
    private static final Map<String, Integer> SUBMENU_OPTIONS = new HashMap<String, Integer>() {{
        put("calendars & events", 1);
        put("products", 2);
        put("rules & admin", 3);
        put("league templates", 4);
        put("content editing", 5);

        put("tracking sheet scorecards", 1);
        put("leagues available for leveling", 2);
        put("division assignment map", 3);
        put("schedules", 4);
        put("jobs", 5);
        put("documents", 6);
        put("volunteer bulk entry", 7);

    }};

    public WebElement getOptionFromMenu(String section, String lnkName) throws Exception {
        List<WebElement> elementList = menuListOptions.findElements(By.className("nav-item"));
        if(lnkName.isEmpty())
            return elementList.get(MENU_SECTIONS.get(section.toLowerCase()));
        List<WebElement> subElements = elementList.get(MENU_SECTIONS.get(section.toLowerCase())).findElements(By.tagName("a"));
        return subElements.get(SUBMENU_OPTIONS.get(lnkName.toLowerCase()));
    }


}

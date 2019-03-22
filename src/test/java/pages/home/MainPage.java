package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPage {

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_LblWelcome")
    private WebElement lblWelcome;

    @FindBy(how = How.ID, using = "welcomeContainer")
    private WebElement lblContainer;

    @FindBy(how = How.ID, using = "navbar-content")
    private WebElement navigator;

    @FindBy(how = How.ID, using = "aspnetForm")
    private WebElement form;

    @FindBy(how = How.ID, using = "scrollChild")
    private WebElement menuListOptions;

    public MainPage(WebDriver driver) {
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

    public WebElement getLinkOptionFromMenu(String section, String lnkName) throws Exception {
        section = section.toLowerCase();
        lnkName = lnkName.toLowerCase();

        List<WebElement> elementList = menuListOptions.findElement(By.id("scrollChild")).findElements(By.className("header-menu__item"));
        if (lnkName.isEmpty())
            return compareAndReturn(elementList, section);

        for (int i = 0; i < elementList.size(); i++) {
            List<WebElement> subList = elementList.get(i).findElements(By.tagName("a"));
            for (int j = 0; j < subList.size(); j++) {
                if(elementList.get(i).getText().toLowerCase().contains(section) &&
                        subList.get(j).getText().toLowerCase().equals(lnkName))
                    return subList.get(j);
            }
        }
        return null;
    }


    private WebElement compareAndReturn(List<WebElement> elementList, String section) {
        for (WebElement element : elementList) {
            if (section.equals(element.getText().toLowerCase()))
                return element;
        }
        return null;
    }
}

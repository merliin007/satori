/*
 * Created by Miguel Angel Aguilar Cuevas
 * 28/08/2018 at 12:08 PM
 */
package pages.decision;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DecisionPlayerPortal {
    @FindBy(how =  How.CLASS_NAME, using = "my-4")
    private WebElement playerDashboard;

    public List<WebElement> getPlayerPortalOptions() {
        return playerDashboard.findElements(By.tagName("a"));
    }

    public DecisionPlayerPortal(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

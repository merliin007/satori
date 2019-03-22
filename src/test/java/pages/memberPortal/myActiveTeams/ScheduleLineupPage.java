/*
 * Created by Miguel Angel Aguilar Cuevas
 * 27/12/2018 at 4:02 PM
 */
package pages.memberPortal.myActiveTeams;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ScheduleLineupPage {
    public ScheduleLineupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_AccordionOneP")
    private WebElement seasonContainer;

    public WebElement getSeasonContainer() {
        return seasonContainer;
    }

    public List<WebElement> getSeasonList(){
        return seasonContainer.findElements(By.className("schedule__sub-item"));
    }

    public WebElement weekLink(WebElement element){
        return element.findElement(By.className("schedule__head-links"));
    }
}

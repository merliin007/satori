/*
 * Created by Miguel Angel Aguilar Cuevas
 * 14/08/2018 at 4:39 PM
 */
package pages.decision;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DecisionPortal {
    //@FindBy(how = How.CSS, using = "#aspnetForm > div:nth-child(14) > div:nth-child(2) > div > div.row.justify-content-center.align-items-center.pb-5")
    @FindBy(how = How.CSS, using = "div.row:nth-child(2)")
    private WebElement container;

    public DecisionPortal(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public List<WebElement> getOptions(){
        return  container.findElements(By.tagName("a"));
    }
}
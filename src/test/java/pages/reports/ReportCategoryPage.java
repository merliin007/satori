/**
 * Created by Miguel Angel Aguilar
 * maaguilar@sciodev.com - feb 2019
 **/

package pages.reports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ReportCategoryPage extends ReportPage {

    public ReportCategoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.CLASS_NAME, using = "pb-5")
    private WebDriver navControls;

    public WebElement getBackToReportsLink() {
        return navControls.findElement(By.className("list-group")).findElements(By.tagName("li")).get(1);
    }

    public WebElement getOfficeOnly() {
        return navControls.findElement(By.className("list-group")).findElements(By.tagName("li")).get(0);
    }
}

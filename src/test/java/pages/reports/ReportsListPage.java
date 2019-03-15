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
import org.openqa.selenium.support.PageFactory;

public class ReportsListPage {

    public ReportsListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CLASS_NAME, using = "reports-list")
    private WebDriver tblReportsList;

    public WebElement getReportRowButton(String reportName) throws Exception{
        for(WebElement row: tblReportsList.findElements(By.className("reports-list__item"))){
            if(row.findElement(By.tagName("h5")).getText().toLowerCase().equals(reportName))
                return row.findElement(By.tagName("a"));
        }
        return null;
    }
}

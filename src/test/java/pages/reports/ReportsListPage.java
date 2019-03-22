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

import java.util.List;

public class ReportsListPage {

    public ReportsListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "ul.list-group")
    private WebElement tblOptions;

    @FindBy(how = How.CLASS_NAME, using = "reports-list")
    private WebElement tblReportsList;

    public WebElement getReportRowButton(String reportName) throws Exception {
        for (WebElement row : tblReportsList.findElements(By.className("reports-list__item"))) {
            if (row.findElement(By.tagName("h5")).getText().toLowerCase().equals(reportName))
                return row.findElement(By.tagName("a"));
        }
        return null;
    }

    public List<WebElement> getAllAvailableReports() throws Exception {
        return tblReportsList.findElements(By.tagName("a"));
    }

    public WebElement getOptionElement(String option) {
        List<WebElement> optList = tblOptions.findElements(By.tagName("a"));
        for (WebElement opt : optList) {
            if (opt.getText().toLowerCase().contains(option)) return opt;
        }
        return null;
    }
}

/**
 * Created by Miguel Angel Aguilar
 * maaguilar@sciodev.com - feb 2019
 **/

package pages.reports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public abstract class ReportPage {

    public ReportPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_rptMain_ctl00")
    private WebElement reportContainer;

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_hlReportFolder")
    private WebElement btnBackToFolder;

    public WebElement getReportContainer() {
        return reportContainer;
    }

    public WebElement getBtnBackToFolder() {
        return btnBackToFolder;
    }
}

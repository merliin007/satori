/**
 * Created by Miguel Angel Aguilar
 * maaguilar@sciodev.com - feb 2019
 **/

package pages.reports;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ReportPage {

    private WebDriver _driver;

    public ReportPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        _driver = driver;

    }

    @FindBy(how = How.ID, using = "ctl00_ctl00_CPHolder_CPHolder_rptMain")
    private WebElement reportContainer;


    public WebElement getReportContainer() {
        return reportContainer;
    }

    public WebElement getBtnBackToFolder() {
        try{
            return _driver.findElement(By.cssSelector("#wrapMain > div.container.py-4 > div.textright-align > p > a"));
        }catch (NoSuchElementException e){
            return _driver.findElement(By.cssSelector("#ctl00_ctl00_CPHolder_CPHolder_hlReportFolder"));
        }
        catch (Exception e){
            return null;
        }

    }

    public WebElement getErrorElement() {
        try {
            return reportContainer.findElement(By.tagName("li"));
        } catch (Exception e) {
            return null;
        }
    }
}

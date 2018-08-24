/*
 * Created by Miguel Angel Aguilar Cuevas
 * 22/08/2018 at 10:19 AM
 */
package pages.newPages.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pages.newPages.Pages;
import utility.job.Job;

import java.util.List;

public abstract class JobPage extends Pages {

    protected Job job;

    @FindBy(how = How.CLASS_NAME, using = "nav-ribbon")
    private WebElement topRibbon;

    public JobPage(WebDriver driver, Job job) {
        super(driver);
        this.job = job;
    }

    public WebElement RibbonOption(String opt) throws Exception{
        List<WebElement> ribonList = topRibbon.findElement(By.className("container"))
                .findElements(By.className("nav-ribbon__option"));
        for(WebElement rib: ribonList){
            if(rib.findElement(By.tagName("a")).getText().toLowerCase().contains(opt))
                return rib;
        }
        return null;
    }

    @Override
    public int getPageActionIndex(String action) {
        return 0;
    }

    @Override
    public int getIndexForHeader(String columnName) {
        return 0;
    }

    @Override
    public List<WebElement> getAllColumnHeaders() {
        return null;
    }


    public abstract WebElement  QueueJobButton();
}

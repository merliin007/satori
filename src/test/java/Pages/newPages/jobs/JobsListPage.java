/*
 * Created by Miguel Angel Aguilar Cuevas
 * 22/08/2018 at 9:17 AM
 */
package pages.newPages.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class JobsListPage {
    @FindBy(how = How.CLASS_NAME, using = "jobs-list")
    private WebElement tblJobList;



    public JobsListPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getJobRowButton(String jobName) throws Exception{
        for(WebElement row: tblJobList.findElements(By.className("jobs-list__item"))){
            if(row.findElement(By.tagName("h5")).getText().contains(jobName))
                return row.findElement(By.tagName("a"));
        }
        return null;
    }


}

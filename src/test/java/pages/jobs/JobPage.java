/*
 * Created by Miguel Angel Aguilar Cuevas
 * 22/08/2018 at 10:19 AM
 */
package pages.jobs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.Pages;
import utility.Helpers;
import utility.job.Job;

import java.util.List;

public abstract class JobPage extends Pages {

    protected Job job;

    public JobPage(WebDriver driver, Job job) {
        super(driver);
        this.job = job;
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
        return getTblResults(1).get(2).findElements(By.tagName("th"));
    }


    public abstract WebElement  QueueJobButton();

    public List<WebElement> getTblPaging(){
        List<WebElement> pagingTable = getTblResults(1).get(0).findElement(By.tagName("tbody")).findElements(By.tagName("td"));
        return pagingTable;
    }

    public void goToNextPage(int idx, Helpers I) {
        List<WebElement> paging = getTblPaging();
        I.Click(paging.get(idx));
    }
}


/*
 * Created by Miguel Angel Aguilar Cuevas
 * 20/07/2018 at 11:01 AM
 */
package pages.newPages;

import com.gargoylesoftware.htmlunit.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public abstract class Pages {

    public abstract List<WebElement> getPopOverLocatorList();

    public List<WebElement> getPopOverCommands(int position){
        return getPopOverLocatorList().get(position)
                .findElement(By.className("popover__commands"))
                .findElements(By.tagName("a"));
    }

    public abstract int getPageActionIndex(String action);

    public abstract int getIndexForHeader(String columnName);

    public abstract List<WebElement> getAllColumnHeaders();

    public abstract List<WebElement> getTblResults(int i);

}

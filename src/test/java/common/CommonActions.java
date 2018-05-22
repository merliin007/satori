//
// File created by miguel_aguilar 02/02/2018 6:00:00 PM
//

package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDateTime;

public class CommonActions {

    private WebDriver _driver;
    private  WebDriverWait wait;

    public CommonActions(WebDriver driver) {
        _driver = driver;
        wait = new WebDriverWait(_driver, 5);
    }

    public void waitUntilElementIsVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementIsAvailable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean IsLocalDateTimeAroundServerDate(LocalDateTime localDate, LocalDateTime serverDate, long tolerance){
        LocalDateTime tempDate = localDate.plusHours(1L);
        return (serverDate.minusMinutes(tolerance).isBefore(tempDate) && serverDate.plusMinutes(tolerance).isAfter(tempDate));
    }



}
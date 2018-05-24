//
// File created by miguel_aguilar 02/02/2018 6:00:00 PM
//

package common;

import Base.CustomExceptions;
import org.openqa.selenium.StaleElementReferenceException;
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

    public void waitUntilElementIsAvailableAfterRefresh(WebElement element){
        //wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
        new WebDriverWait(_driver, 10)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
    }

    public boolean IsLocalDateTimeAroundServerDate(LocalDateTime localDate, LocalDateTime serverDate, long tolerance){
        LocalDateTime tempDate = localDate.plusHours(1L);
        return (serverDate.minusMinutes(tolerance).isBefore(tempDate) && serverDate.plusMinutes(tolerance).isAfter(tempDate));
    }

    public void verifyAlertErrorAndAcceptIt() {
        if(wait.until(ExpectedConditions.alertIsPresent()) != null) {
            _driver.switchTo().alert().accept();
            _driver.switchTo().defaultContent();
        }
    }


}

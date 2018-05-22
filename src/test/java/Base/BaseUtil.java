package Base;

import common.SuiteSetUp;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class BaseUtil {

    public WebDriver driver;

    public void GrabScreenShot()  {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("target/screenshots/screenshot-" + System.currentTimeMillis()+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void NavigateToPage(String environment){
        switch (environment.toLowerCase()){
            case "qa":
                driver.navigate().to(SuiteSetUp.QA_ENVIRONMENT);
                break;
            case "test":
                driver.navigate().to(SuiteSetUp.TEST_ENVIRONMENT);
                break;
        }
    }

}

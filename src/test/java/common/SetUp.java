//
// File created by miguel_aguilar 02/02/2018 6:00:00 PM
//

package common;



import base.BaseUtil;
import org.testng.annotations.Parameters;
import utility.Log;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static java.lang.String.join;


public class SetUp extends BaseUtil {

    private BaseUtil base;

    public SetUp(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario){
        File driver_exe = null;

        switch (SuiteSetUp.BROWSER.toLowerCase()){
            case "firefox":
                driver_exe = new File(SuiteSetUp.WEBDRIVERS_FOLDER, "geckodriver64.exe");
                System.setProperty("webdriver.gecko.driver", driver_exe.getAbsolutePath());
                FirefoxProfile profile = new FirefoxProfile();
                //profile.setPreference("browser.private.browsing.autostart",true);
                profile.setAcceptUntrustedCertificates(true);
                base.driver = new FirefoxDriver();
                break;
            case "chrome":
                driver_exe = new File(SuiteSetUp.WEBDRIVERS_FOLDER,"chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", driver_exe.getAbsolutePath());
                DesiredCapabilities capabilities = new DesiredCapabilities().chrome();
                capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
                base.driver = new ChromeDriver();
                break;
            case "edge":
                driver_exe = new File(SuiteSetUp.WEBDRIVERS_FOLDER,"MicrosoftWebDriver.exe");
                System.setProperty("webdriver.edge.driver", driver_exe.getAbsolutePath());
                base.driver = new EdgeDriver();
                break;
                default:
                    System.out.println("Browser not defined");
                    System.exit(-1);
        }

        base.driver.manage().timeouts().implicitlyWait(10L,TimeUnit.SECONDS);
        base.driver.manage().window().maximize();
        this.base.NavigateToPage(System.getProperty("environmentName"));
        Log.startTestCase(scenario.getName());
    }
    @After
    public void TearDownTest(Scenario scenario) {
        if (scenario.isFailed())
            Log.endTestCase(scenario.getName() + "\tFAILED");
        else
            Log.endTestCase(scenario.getName() + "\tPASSED");
        new TearDown(base);
    }

}

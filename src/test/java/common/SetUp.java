//
// File created by miguel_aguilar 02/02/2018 6:00:00 PM
//

package common;


import base.BaseUtil;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
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
    public void InitializeTest(Scenario scenario) {
        File driver_exe = null;
        boolean isIe = false;
        switch (SuiteSetUp.BROWSER.toLowerCase()) {
            case "firefox":
                driver_exe = new File(SuiteSetUp.WEBDRIVERS_FOLDER, "geckodriver64.exe");
                System.setProperty("webdriver.gecko.driver", driver_exe.getAbsolutePath());
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                firefoxOptions.setCapability("browser.private.browsing.autostart", true);
                base.driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
                driver_exe = new File(SuiteSetUp.WEBDRIVERS_FOLDER, "chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", driver_exe.getAbsolutePath());
                ChromeOptions options = new ChromeOptions();
                //options.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
                options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                base.driver = new ChromeDriver(options);
                break;
            case "ie":
                driver_exe = new File(SuiteSetUp.WEBDRIVERS_FOLDER, "IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", driver_exe.getAbsolutePath());
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                ieOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                base.driver = new InternetExplorerDriver();
                isIe = true;
                break;
            default:
                System.out.println("Browser not defined");
                System.exit(-1);
        }

        base.driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        base.driver.manage().window().maximize();
        this.base.NavigateToPage(System.getProperty("environmentName"), isIe);

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

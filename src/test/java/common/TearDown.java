//
// File created by miguel_aguilar 02/02/2018 6:00:00 PM
//

package common;

import base.BaseUtil;

public class TearDown {
    public TearDown(BaseUtil base){
        try {
            base.driver.quit();
        }
        catch (Exception e) {

        }
        finally {
            //killBrowsers(SuiteSetUp.BROWSER + ".exe");
            System.out.println("Killing all browsers");
        }
    }
    private void killBrowsers(String browserToBeKilled) {
        try {
            Process process = Runtime.getRuntime().exec("taskkill /im " + browserToBeKilled + " /t");
            process.waitFor();
        } catch (Exception e) {
            System.out.println("Issues during browsers closing." + e.getMessage());
        }
    }
}

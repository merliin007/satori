/**
 * Created by Miguel Angel Aguilar
 * maaguilar@sciodev.com - feb 2019
 **/

package steps;

import base.BaseUtil;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;
import pages.reports.ReportCategoryPage;
import pages.reports.ReportPage;
import pages.reports.ReportsListPage;
import utility.Helpers;
import utility.Log;
import utility.SpreadSheetReader;
import utility.roles.PagesByRole;

import java.util.List;

import static org.testng.Assert.*;


public class ReportsSteps {
    private List<String> allowedReports;
    private BaseUtil base;
    private Helpers I;
    private ReportsListPage reportsListPage;

    public ReportsSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base.driver);
    }

    @Then("^I visit each of the reports granted for ([^\"]*)$")
    public void iVisitEachOfTheReportsGrantedForRole(String roleName) {
        try {
            Log.info("Visiting reports granted for: " + roleName);
            reportsListPage = new ReportsListPage(base.driver);

            int i = 0;
            while (true) {
                List<WebElement> availableReports = reportsListPage.getAllAvailableReports();
                I.Click(availableReports.get(i));

                int j = 0;
                while (true) {
                    ReportsListPage subRep = new ReportsListPage(base.driver);
                    List<WebElement> subList = subRep.getAllAvailableReports();
                    I.Click(subList.get(j));

                    ReportPage reportPage = new ReportPage(base.driver);
                    assertNull(reportPage.getErrorElement());
                    I.Click(reportPage.getBtnBackToFolder());


                    if (!(++j < subList.size())) break;
                }
                I.Click(reportsListPage.getOptionElement("back"));
                if (!(++i < availableReports.size())) break;
            }


        } catch (Exception e) {
            Log.error(e.getMessage());
            base.GrabScreenShot();
            fail();
        }

    }

    private PagesByRole getPages(String roleName) {
        switch (roleName.toLowerCase()) {
            case "coordinator":
                return PagesByRole.COORDINATOR;
            case "it committee":
                return PagesByRole.IT_COMMITTEE;
            case "league vp":
                return PagesByRole.LEAGUE_VP;
            case "overall coordinator":
                return PagesByRole.OVERALL_COORDINATOR;
            case "president":
                return PagesByRole.PRESIDENT;
            case "junior ladder manager":
                return PagesByRole.JUNIOR_LADDER_MANAGER;
            case "vp jr challenge ladder":
                return PagesByRole.VP_Jr_CHALLENGE_LADDER;
            default:
                return null;
        }
    }
}

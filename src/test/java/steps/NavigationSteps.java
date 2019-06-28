package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import pages.home.MainPage;
import pages.navigation.GenericPage;
import utility.Helpers;
import utility.Log;
import utility.xml.XMLReader;

import java.util.*;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import static utility.Helpers.AddErrorPage;
import static utility.Helpers.getErrorPages;


public class NavigationSteps {
    private BaseUtil base;
    private MainPage mainPage;
    private Helpers I;
    private List<List<String>> compare = new ArrayList<>();


    public NavigationSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base);
    }


    @And("I navigate to the following page option")
    public void iNavigateToTheFollowingPageOption(DataTable links) {
        try {
            mainPage = new MainPage(base.driver);
            List<List<String>> data = links.raw();
            for (List<String> row : data) {
                Log.info("Navigating to " + row.get(1) + " page");
                I.Click(mainPage.getNavigator()); // Code for using Navigator Menu
                try {
                    I.Click(mainPage.getLinkOptionFromMenu(row.get(0), row.get(1)));
                    Log.info(String.valueOf(I.CompareExpectedPage(row.get(1))));
                } catch (Exception ee) {
                    AddErrorPage(row);
                    base.GrabScreenShot();
                    Log.error("Error during navigation to: " + row.get(0) + " - " + row.get(1));
                } finally {
                    if (!row.get(2).isEmpty() && row.get(2).equals("true"))
                        I.GoBackToPreviousPage();
                }
            }

        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error("Error during navigation to: ");
            fail();
        }
    }

    @Then("^No error is shown$")
    public void noErrorIsShown() {
        if (getErrorPages().isEmpty())
            Log.info("No errors were found accessing all pages");
        else
            Log.info("There was an error accessing: " + Collections.singleton(getErrorPages()));
        assertTrue(getErrorPages().isEmpty());
    }

    @And("^I start navigating URLs from XML file being redirected to new site correctly$")
    public void iStartNavigatingURLsFromXMLFileBeingRedirectedToNewSiteCorrectly() {
        try {
            XMLReader xmlReader = new XMLReader("redirections.xml");
            Map<String, String> urls = xmlReader.getURLS();

            Iterator it = null;
            if (urls != null)
                it = urls.keySet().iterator();
            else
                fail();

            while (it.hasNext()) {
                String from = "";
                String to = "";
                List<String> tmp = new ArrayList<>();

                try {

                    from = (String) it.next();
                    to = urls.get(from);
                    Log.info("From: " + from + " -> To: " + to);
                    base.NavigateToURL(from);

                    String actualURL = base.GetPageURL();

                    GenericPage page = new GenericPage(base.driver);

                    if (!actualURL.substring(22).contains(to) || actualURL.substring(22).contains("Unexpected") || page.getDivError().isDisplayed()) {
                        tmp.add(from);
                        tmp.add(to);
                        tmp.add(actualURL);

                        Log.error("Visiting: " + from + " expecting: " + to + " actual: " + actualURL);
                    }

                } catch (UnhandledAlertException e) {
                    I.verifyAlertErrorAndAcceptIt();

                }catch (NoSuchElementException e){}
                catch (Exception e) {
                    Log.error("Error accessing: " + from);
                    String stackTrace = ExceptionUtils.getStackTrace(e);
                    Log.error(stackTrace);
                }
                if (!tmp.isEmpty())
                    compare.addAll(Collections.singleton(tmp));
            }


        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error("Error during navigation to: ");
            fail();
        }
    }

    /*@And("^I create a file with redirected urls$")
    public void iCreateAFileWithRedirectedUrls() {
        try {
            File file = new File("url_redirections.txt");
            file.createNewFile();

            FileWriter fw = new FileWriter(file);


            Iterator it = redirect.keySet().iterator();
            while (it.hasNext()) {
                String from = (String) it.next();
                fw.write("From: " + from + " => " + redirect.get(from) + "\n");
//                Thread.sleep(2000L);
            }

            fw.flush();
            fw.close();

        } catch (Exception e) {
            Log.error(e.getMessage());
        }

    }*/

    @Then("^I print results$")
    public void iPrintResults() {
        if(compare.isEmpty())
            Log.info("******* No errors navigating all pages *******");
        else {
            Log.info("******* The following pages had errors while being visited *******");
            for (List<String> tmp : compare) {
                Log.error("From: " + tmp.get(0) + " to: " + tmp.get(1) + " -> " + tmp.get(2));
            }
        }
    }
}

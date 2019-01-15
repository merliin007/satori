/*
 * Created by Miguel Angel Aguilar Cuevas
 * 22/08/2018 at 9:07 AM
 */
package steps;

import base.BaseUtil;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import pages.jobs.*;
import utility.Helpers;
import utility.Log;
import utility.job.Job;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.fail;

public class JobSteps {
    private BaseUtil base;
    private ArrayList<JobPage> jobs;
    private Helpers I;
    private JobsListPage jobsListPage;

    public JobSteps(BaseUtil base) {
        this.base = base;
        I = new Helpers(base);
    }


    @And("^I visit each of the following jobs and successfully queue them$")
    public void iVisitEachOfTheFollowingJobsAndSuccessfullyQueueThem(DataTable table){
        try {
            jobsListPage = new JobsListPage(base.driver);
            List<Job> jobList = table.asList(Job.class);
            jobs = I.initJobVariables(jobList);
            int i = 0;
            for(JobPage job: jobs ){
                I.Click(jobsListPage.getJobRowButton(jobList.get(i).getJobName()));
                I.Click(job.QueueJobButton());
                I.Click(job.RibbonOption("jobs"));
                i++;
            }

        } catch (Exception e) {
            base.GrabScreenShot();
            Log.error(e.getMessage());
            fail();
        }
    }

    @Then("^I can verify all jobs are listed on the job queue page$")
    public void iCanVerifyAllJobsAreListedOnTheJobQueuePage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


}

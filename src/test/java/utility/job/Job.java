/*
 * Created by Miguel Angel Aguilar Cuevas
 * 22/08/2018 at 12:07 PM
 */
package utility.job;

public class Job {
    private String jobName;
    private String year;
    private String season;
    private String league;
    private String age;
    private String levelFlight;

    public Job(String jobName, String year, String season, String league, String age, String levelFlight) {
        this.jobName = jobName;
        this.year = year;
        this.season = season;
        this.league = league;
        this.age = age;
        this.levelFlight = levelFlight;
    }

    public String getJobName() {
        return jobName;
    }

    public String getYear() {
        return year;
    }

    public String getSeason() {
        return season;
    }

    public String getLeague() {
        return league;
    }

    public String getAge() {
        return age;
    }

    public String getLevelFlight() {
        return levelFlight;
    }
}

/*
 * Created by Miguel Angel Aguilar Cuevas
 * 11/01/2019 at 12:12 PM
 */
package utility.tss;

import com.sun.istack.NotNull;

public class TrackingSheetScoreCard {

    private int week;
    private String homeResult;
    private String homePlayer1;
    private String homePlayer2;
    private String homeSet1;
    private String homeSet2;
    private String homeSet3;
    private String playedDate;
    private String awayResult;
    private String awayPlayer1;
    private String awayPlayer2;
    private String awaySet1;
    private String awaySet2;
    private String awaySet3;
    private String homeComments;
    private String awayComments;

    public TrackingSheetScoreCard(@NotNull int week, String homeResult, String homePlayer1, String homePlayer2, String homeSet1, String homeSet2, String homeSet3, String playedDate,
                                 String awaySet1, String awaySet2, String awaySet3, String awayResult, String awayPlayer1, String awayPlayer2, String homeComments, String awayComments) {
        this.week = week;
        this.homeResult = homeResult;
        this.homePlayer1 = homePlayer1;
        this.homePlayer2 = homePlayer2;
        this.homeSet1 = homeSet1;
        this.homeSet2 = homeSet2;
        this.homeSet3 = homeSet3;
        this.playedDate = playedDate;
        this.awayResult = awayResult;
        this.awayPlayer1 = awayPlayer1;
        this.awayPlayer2 = awayPlayer2;
        this.awaySet1 = awaySet1;
        this.awaySet2 = awaySet2;
        this.awaySet3 = awaySet3;
        this.homeComments = homeComments;
        this.awayComments = awayComments;
    }

    public int getWeek() {
        return week;
    }

    public String getHomeResult() {
        return homeResult;
    }

    public String getHomePlayer1() {
        return homePlayer1;
    }

    public String getHomePlayer2() {
        return homePlayer2;
    }

    public String getHomeSet1() {
        return homeSet1;
    }

    public String getHomeSet2() {
        return homeSet2;
    }

    public String getHomeSet3() {
        return homeSet3;
    }

    public String getPlayedDate() {
        return playedDate;
    }

    public String getAwayResult() {
        return awayResult;
    }

    public String getAwayPlayer1() {
        return awayPlayer1;
    }

    public String getAwayPlayer2() {
        return awayPlayer2;
    }

    public String getAwaySet1() {
        return awaySet1;
    }

    public String getAwaySet2() {
        return awaySet2;
    }

    public String getAwaySet3() {
        return awaySet3;
    }

    public String getHomeComments() {
        return homeComments;
    }

    public String getAwayComments() {
        return awayComments;
    }
}

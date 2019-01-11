/*
 * Created by Miguel Angel Aguilar Cuevas
 * 11/01/2019 at 12:49 PM
 */
package utility.tss;

import org.openqa.selenium.WebElement;

public class TssWeekElements {
    private WebElement homeResult;
    private WebElement homePlayer1;
    private WebElement homePlayer2;
    private WebElement homeSet1;
    private WebElement homeSet2;
    private WebElement homeSet3;
    private WebElement playedDate;
    private WebElement awaySet1;
    private WebElement awaySet2;
    private WebElement awaySet3;
    private WebElement awayResult;
    private WebElement awayPlayer1;
    private WebElement awayPlayer2;

    public TssWeekElements(WebElement homeResult, WebElement homeSet1, WebElement homeSet2, WebElement homeSet3,
                           WebElement playedDate, WebElement awaySet1, WebElement awaySet2, WebElement awaySet3,
                           WebElement awayResult) {
        this.homeResult = homeResult;
        this.homeSet1 = homeSet1;
        this.homeSet2 = homeSet2;
        this.homeSet3 = homeSet3;
        this.playedDate = playedDate;
        this.awaySet1 = awaySet1;
        this.awaySet2 = awaySet2;
        this.awaySet3 = awaySet3;
        this.awayResult = awayResult;
    }

    public TssWeekElements(WebElement homeResult, WebElement homePlayer1, WebElement homePlayer2, WebElement homeSet1,
                           WebElement homeSet2, WebElement homeSet3, WebElement playedDate, WebElement awaySet1,
                           WebElement awaySet2, WebElement awaySet3, WebElement awayResult, WebElement awayPlayer1,
                           WebElement awayPlayer2) {
        this.homeResult = homeResult;
        this.homePlayer1 = homePlayer1;
        this.homePlayer2 = homePlayer2;
        this.homeSet1 = homeSet1;
        this.homeSet2 = homeSet2;
        this.homeSet3 = homeSet3;
        this.playedDate = playedDate;
        this.awaySet1 = awaySet1;
        this.awaySet2 = awaySet2;
        this.awaySet3 = awaySet3;
        this.awayResult = awayResult;
        this.awayPlayer1 = awayPlayer1;
        this.awayPlayer2 = awayPlayer2;
    }

    public WebElement getHomeResult() {
        return homeResult;
    }

    public WebElement getHomePlayer1() {
        return homePlayer1;
    }

    public WebElement getHomePlayer2() {
        return homePlayer2;
    }

    public WebElement getHomeSet1() {
        return homeSet1;
    }

    public WebElement getHomeSet2() {
        return homeSet2;
    }

    public WebElement getHomeSet3() {
        return homeSet3;
    }

    public WebElement getPlayedDate() {
        return playedDate;
    }

    public WebElement getAwaySet1() {
        return awaySet1;
    }

    public WebElement getAwaySet2() {
        return awaySet2;
    }

    public WebElement getAwaySet3() {
        return awaySet3;
    }

    public WebElement getAwayResult() {
        return awayResult;
    }

    public WebElement getAwayPlayer1() {
        return awayPlayer1;
    }

    public WebElement getAwayPlayer2() {
        return awayPlayer2;
    }

    public void setHomePlayer1(WebElement homePlayer1) {
        this.homePlayer1 = homePlayer1;
    }

    public void setHomePlayer2(WebElement homePlayer2) {
        this.homePlayer2 = homePlayer2;
    }

    public void setAwayPlayer1(WebElement awayPlayer1) {
        this.awayPlayer1 = awayPlayer1;
    }

    public void setAwayPlayer2(WebElement awayPlayer2) {
        this.awayPlayer2 = awayPlayer2;
    }
}

/*
 * Created by Miguel Angel Aguilar Cuevas
 * 18/01/2019 at 12:09 PM
 */
package utility.payment;

public class PurchaseDetail {
    private String purchaseType;
    private String year;
    private String ladder;
    private String tournament;
    private String membership;
    private String upgrade;
    private String purchaseAmount;
    private String comments;

    public PurchaseDetail(String purchaseType, String year, String ladder, String tournament, String membership, String upgrade, String purchaseAmount, String comments) {
        this.purchaseType = purchaseType;
        this.year = year;
        this.ladder = ladder;
        this.tournament = tournament;
        this.membership = membership;
        this.upgrade = upgrade;
        this.purchaseAmount = purchaseAmount;
        this.comments = comments;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public String getYear() {
        return year;
    }

    public String getLadder() {
        return ladder;
    }

    public String getTournament() {
        return tournament;
    }

    public String getMembership() {
        return membership;
    }

    public boolean getUpgrade() {
        return upgrade.matches("yYes|tTrue");
    }

    public String getPurchaseAmount() {
        return purchaseAmount;
    }

    public String getComments() {
        return comments;
    }
}

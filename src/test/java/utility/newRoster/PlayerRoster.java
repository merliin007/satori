/*
 * Created by Miguel Angel Aguilar Cuevas
 * 29/08/2018 at 2:07 PM
 */
package utility.newRoster;

public class PlayerRoster {

    private String aLTA_Number;
    private String first;
    private String last;
    private String captain;
    private String coCaptain;
    private String designee;

    public String getaLTA_Number() {
        return aLTA_Number;
    }


    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public boolean isCaptain() {
        return captain.toLowerCase().contains("yes");
    }

    public boolean isCoCaptain() {
        return coCaptain.toLowerCase().contains("yes");
    }

    public boolean isDesignee() {
        return designee.toLowerCase().contains("yes");
    }

    public PlayerRoster(String aLTA_Number, String first, String last, String captain, String coCaptain, String designee) {
        this.aLTA_Number = aLTA_Number;
        this.first = first;
        this.last = last;
        this.captain = captain;
        this.coCaptain = coCaptain;
        this.designee = designee;
    }
}

/*
 * Created by Miguel Angel Aguilar Cuevas
 * 04/01/2019 at 3:25 PM
 */
package utility.members;

public class Member {

    private String altaNum;
    private String first;
    private String last;
    private String city;
    private String zip;
    private String dob;
    private String home;

    public Member(String altaNum, String first, String last, String city, String zip, String dob, String home) {
        this.altaNum = altaNum;
        this.first = first;
        this.last = last;
        this.city = city;
        this.zip = zip;
        this.dob = dob;
        this.home = home;
    }

    public Member(String altaNum) {
        this.altaNum = altaNum;
    }

    public String getAltaNum() {
        return altaNum;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getDob() {
        return dob;
    }

    public String getHome() {
        return home;
    }
}

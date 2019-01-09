/*
 * Created by Miguel Angel Aguilar Cuevas
 * 04/01/2019 at 3:25 PM
 */
package utility.members;

import com.sun.istack.NotNull;

import java.util.List;

public class Member {

    private String altaNum;
    private String first;
    private String last;
    private String city;
    private String zip;
    private String dob;
    private String gender;
    private String wheelChair;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String email;
    private String street;
    private String apt;
    private String state;
    private String county;
    private String userName;
    private String password;

    public Member(String FirstName, String LastName, String Gender, String DOB, String WheelChair, String HomePhone, String MobilePhone, String WorkPhone,
                  @NotNull String Email, String Street, String Apt, String City, String State, String Zip, String County, String UserName, String Password) {

        String systemTime = String.valueOf(System.currentTimeMillis());

        this.first = FirstName + systemTime;
        this.last = LastName + systemTime;
        this.city = City;
        this.zip = Zip;
        this.dob = DOB;
        this.gender = Gender;
        this.wheelChair = WheelChair;
        this.homePhone = HomePhone;
        this.mobilePhone = MobilePhone;
        this.workPhone = WorkPhone;
        if (!Email.contains("@"))
            throw new IllegalArgumentException("Email does not have correct format");
        else {
            String[] tmp = Email.split("(?=@)");
            this.email = tmp[0] + systemTime + tmp[1];
        }
        this.street = Street;
        this.apt = Apt;
        this.state = State;
        this.county = County;
        this.userName = UserName + systemTime;
        this.password = Password;
    }

    public Member(List<String> tbl) {
        this(tbl.get(0), tbl.get(1), tbl.get(2), tbl.get(3), tbl.get(4), tbl.get(5), tbl.get(6), tbl.get(7),
                tbl.get(8), tbl.get(9), tbl.get(10), tbl.get(11), tbl.get(12), tbl.get(13), tbl.get(14), tbl.get(15), tbl.get(16));
    }

    public Member(String altaNum, String first, String last, String city, String zip, String dob) {
        this.altaNum = altaNum;
        this.first = first;
        this.last = last;
        this.city = city;
        this.zip = zip;
        this.dob = dob;
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

    public String getGender() {
        return gender;
    }

    public boolean getWheelChair() {
        return wheelChair.matches("[yY]es|[tT]rue");
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getApt() {
        return apt;
    }

    public String getState() {
        return state;
    }

    public String getCounty() {
        return county;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}

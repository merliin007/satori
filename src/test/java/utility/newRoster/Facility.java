/*
 * Created by Miguel Angel Aguilar Cuevas
 * 31/08/2018 at 6:02 PM
 */
package utility.newRoster;

public class Facility {
    private String name;
    private String city;
    private String county;
    private String id;

    public Facility(String id, String name, String city, String county) {
        this.name = name;
        this.city = city;
        this.county = county;
        this.id = id;
    }

    public Facility(String name, String city, String id) {
        this(id, name, city, "" );
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getId() {
        return id;
    }
}

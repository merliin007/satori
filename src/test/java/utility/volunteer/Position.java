/*
 * Created by Miguel Angel Aguilar Cuevas
 * 08/01/2019 at 2:44 PM
 */
package utility.volunteer;

public class Position {
    private String position;
    private String year;
    private String comments;
    private String league;
    private String season;

    public Position(String Position, String Year, String Comments, String League, String Season) {
        this.position = Position;
        this.year = Year;
        this.comments = Comments;
        this.league = League;
        this.season = Season;
    }

    public String getPosition() {
        return position;
    }

    public String getYear() {
        return year;
    }

    public String getComments() {
        return comments;
    }

    public String getLeague() {
        return league;
    }

    public String getSeason() {
        return season;
    }
}


/*
 * Created by Miguel Angel Aguilar Cuevas
 * 15/08/2018 at 5:18 PM
 */
package utility.league;

class LeagueDescription {

    private String LeagueType;
    private String Year;
    private String Season;
    private String Playday;
    private String VPName;
    private String RosterId;
    private String PacketId;
    private String AgeType;
    private String MinAge;
    private String MaxAge;
    private boolean Lights;
    private boolean Tiebreaker;

    public String getLeagueType() {
        return LeagueType;
    }

    public String getYear() {
        return Year;
    }

    public String getSeason() {
        return Season;
    }

    public String getPlayday() {
        return Playday;
    }

    public String getVPName() {
        return VPName;
    }

    public String getRosterId() {
        return RosterId;
    }

    public String getPacketId() {
        return PacketId;
    }

    public String getAgeType() {
        return AgeType;
    }

    public String getMinAge() {
        return MinAge;
    }

    public String getMaxAge() {
        return MaxAge;
    }

    public boolean isLights() {
        return Lights;
    }

    public boolean isTiebreaker() {
        return Tiebreaker;
    }

    public LeagueDescription(String leagueType, String year, String season, String playday, String VPName, String rosterId,
                             String packetId, String ageType, String minAge, String maxAge, boolean lights, boolean tiebreaker) {
        LeagueType = leagueType;
        Year = year;
        Season = season;
        Playday = playday;
        this.VPName = VPName;
        RosterId = rosterId;
        PacketId = packetId;
        AgeType = ageType;
        MinAge = minAge;
        MaxAge = maxAge;
        Lights = lights;
        Tiebreaker = tiebreaker;
    }
}
class LeagueDetails{
    //TM | RetTM | MatchP | MaxAdd | MinFem | RetFem | MinMales | RetMal | TMNeeded | FemNeeded | MalNeed |
    private String TM;
    private String RetTM;
    private String MatchPlayers;
    private String MaxAdd;
    private String MinFem;
    private String RetFem;
    private String MinMales;
    private String RetMal;
    private String TMNeeded;
    private String FemNeeded;
    private String MalNeed;

    public String getTM() {
        return TM;
    }

    public String getRetTM() {
        return RetTM;
    }

    public String getMatchPlayers() {
        return MatchPlayers;
    }

    public String getMaxAdd() {
        return MaxAdd;
    }

    public String getMinFem() {
        return MinFem;
    }

    public String getRetFem() {
        return RetFem;
    }

    public String getMinMales() {
        return MinMales;
    }

    public String getRetMal() {
        return RetMal;
    }

    public String getTMNeeded() {
        return TMNeeded;
    }

    public String getFemNeeded() {
        return FemNeeded;
    }

    public String getMalNeed() {
        return MalNeed;
    }

    public LeagueDetails(String TM, String retTM, String matchPlayers, String maxAdd, String minFem, String retFem,
                         String minMales, String retMal, String TMNeeded, String femNeeded, String malNeed) {
        this.TM = TM;
        RetTM = retTM;
        MatchPlayers = matchPlayers;
        MaxAdd = maxAdd;
        MinFem = minFem;
        RetFem = retFem;
        MinMales = minMales;
        RetMal = retMal;
        this.TMNeeded = TMNeeded;
        FemNeeded = femNeeded;
        MalNeed = malNeed;
    }
}

class LeagueDates{
    private String EndDate;
    private String CaptMeeting;
    private String PlayWeek;

    public String getEndDate() {
        return EndDate;
    }

    public String getCaptMeeting() {
        return CaptMeeting;
    }

    public String getPlayWeek() {
        return PlayWeek;
    }

    public LeagueDates(String endDate, String captMeeting, String playWeek) {
        EndDate = endDate;
        CaptMeeting = captMeeting;
        PlayWeek = playWeek;
    }
}

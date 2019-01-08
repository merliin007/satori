/*
 * Created by Miguel Angel Aguilar Cuevas
 * 15/08/2018 at 5:18 PM
 */
package utility.league;

public class LeagueComponents {

    public class LeagueDescription {
        private String leagueName;
        private String gender;
        private String minAgeType;
        private String numberOfSeasonsPerYear;

        private String leagueType;
        private String year;
        private String season;
        private String playDay;
        private String vPName;
        private String rosterDocId;
        private String packetDocId;
        private String ageType;
        private String minAge;
        private String maxAge;
        private String scoreCardType;
        private boolean lights;
        private boolean tiebreaker;
        //        private boolean  active;
        private String active;

        public String getLeagueName() {
            return leagueName;
        }

        public String getGender() {
            return gender;
        }

        public String getMinAgeType() {
            return minAgeType;
        }

        public String getNumberOfSeasonsPerYear() {
            return numberOfSeasonsPerYear;
        }

        public String getLeagueType() {
            return leagueType;
        }

        public String getYear() {
            return year;
        }

        public String getSeason() {
            return season;
        }

        public String getPlayday() {
            return playDay;
        }

        public String getVPName() {
            return vPName;
        }

        public String getRosterDocId() {
            return rosterDocId;
        }

        public String getPacketDocId() {
            return packetDocId;
        }

        public String getAgeType() {
            return ageType;
        }

        public String getMinAge() {
            return minAge;
        }

        public String getMaxAge() {
            return maxAge;
        }

        public String getScoreCardType() {
            return scoreCardType;
        }

        public boolean isLights() {
            return lights;
        }

        public boolean isTiebreaker() {
            return tiebreaker;
        }

        public boolean isActive() {
            return active.matches("[yY]es|[tT]rue");
        }

        public LeagueDescription(String LeagueType, String Year, String Season, String PlayDay, String VPName, String RosterDocId,
                                 String PacketDocId, String AgeType, String MinAge, String MaxAge, String ScoreCardType, String Lights, String Tiebreaker) {
            leagueType = LeagueType;
            year = Year;
            season = Season;
            playDay = PlayDay;
            vPName = VPName;
            rosterDocId = RosterDocId;
            packetDocId = PacketDocId;
            ageType = AgeType;
            minAge = MinAge;
            maxAge = MaxAge;
            scoreCardType = ScoreCardType;
            lights = Lights.contains("yes");
            tiebreaker = Tiebreaker.contains("yes");
        }

        public LeagueDescription(String LeagueName, String Gender, String MinAgeType, String MinAge, String MaxAge, String ScoreCardType, String NumberOfSeasonsPerYear, String Active) {
            leagueName = LeagueName;
            gender = Gender;
            minAgeType = MinAgeType;
            minAge = MinAge;
            maxAge = MaxAge;
            scoreCardType = ScoreCardType;
            numberOfSeasonsPerYear = NumberOfSeasonsPerYear;
            active = Active;
        }

        public LeagueDescription(String Year, String Season, String LeagueType){
            year = Year;
            season = Season;
            leagueType = LeagueType;
        }
    }

    public class LeagueDetails {

        private String minTeamMembers;
        private String minRetTeamMembers;
        private String minMatchPlayers;
        private String maxAddons;
        private String minFemales;
        private String minRetFemales;
        private String minMales;
        private String minRetMales;
        private String minTeamMembersNeeded;
        private String minFemalesNeeded;
        private String minMalesNeeded;

        public String getMinTeamMembers() {
            return minTeamMembers;
        }

        public String getMinRetTeamMembers() {
            return minRetTeamMembers;
        }

        public String getMinMatchPlayers() {
            return minMatchPlayers;
        }

        public String getMaxAddons() {
            return maxAddons;
        }

        public String getMinFemales() {
            return minFemales;
        }

        public String getMinRetFemales() {
            return minRetFemales;
        }

        public String getMinMales() {
            return minMales;
        }

        public String getMinRetMales() {
            return minRetMales;
        }

        public String getMinTeamMembersNeeded() {
            return minTeamMembersNeeded;
        }

        public String getMinFemalesNeeded() {
            return minFemalesNeeded;
        }

        public String getMinMalesNeeded() {
            return minMalesNeeded;
        }

        public LeagueDetails(String minTeamMembers, String minRetTeamMembers, String minMatchPlayers, String maxAddons, String minFemales, String minRetFemales,
                             String minMales, String minRetMales, String minTeamMembersNeeded, String minFemalesNeeded, String minMalesNeeded) {
            this.minTeamMembers = minTeamMembers;
            this.minRetTeamMembers = minRetTeamMembers;
            this.minMatchPlayers = minMatchPlayers;
            this.maxAddons = maxAddons;
            this.minFemales = minFemales;
            this.minRetFemales = minRetFemales;
            this.minMales = minMales;
            this.minRetMales = minRetMales;
            this.minTeamMembersNeeded = minTeamMembersNeeded;
            this.minFemalesNeeded = minFemalesNeeded;
            this.minMalesNeeded = minMalesNeeded;
        }
    }


    public class LeagueDates {
        private String endDate;
        private String captMeeting;
        private String playWeek;

        public String getEndDate() {
            return endDate;
        }

        public String getCaptMeeting() {
            return captMeeting;
        }

        public String getPlayWeek() {
            return playWeek;
        }

        public LeagueDates(String EndDate, String CaptMeeting, String PlayWeek) {
            this.endDate = EndDate;
            this.captMeeting = CaptMeeting;
            this.playWeek = PlayWeek;
        }
    }

    public class LeagueSeasons {
        private String season;

        public LeagueSeasons(String season) {
            this.season = season;
        }

        public String getSeason() {
            return season;
        }
    }

    public class LeagueExclusions {
        private String excludedLeague;

        public LeagueExclusions(String excludedLeague) {
            this.excludedLeague = excludedLeague;
        }

        public String getExcludedLeague() {
            return excludedLeague;
        }
    }

}

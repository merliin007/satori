/*
 * Created by Miguel Angel Aguilar Cuevas
 * 15/08/2018 at 5:18 PM
 */
package utility.league;

public class LeagueComponents {

    public class LeagueDescription {
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
    }

    public class LeagueDetails {

        private String tM;
        private String retTM;
        private String matchPlayers;
        private String maxAdd;
        private String minFem;
        private String retFem;
        private String minMales;
        private String retMal;
        private String tMNeeded;
        private String femNeeded;
        private String malNeed;

        public String getTM() {
            return tM;
        }

        public String getRetTM() {
            return retTM;
        }

        public String getMatchPlayers() {
            return matchPlayers;
        }

        public String getMaxAdd() {
            return maxAdd;
        }

        public String getMinFem() {
            return minFem;
        }

        public String getRetFem() {
            return retFem;
        }

        public String getMinMales() {
            return minMales;
        }

        public String getRetMal() {
            return retMal;
        }

        public String getTMNeeded() {
            return tMNeeded;
        }

        public String getFemNeeded() {
            return femNeeded;
        }

        public String getMalNeed() {
            return malNeed;
        }

        public LeagueDetails(String TM, String RetTM, String MatchPlayers, String MaxAdd, String MinFem, String RetFem,
                             String MinMales, String RetMal, String TMNeeded, String FemNeeded, String MalNeed) {
            this.tM = TM;
            this.retTM = RetTM;
            this.matchPlayers = MatchPlayers;
            this.maxAdd = MaxAdd;
            this.minFem = MinFem;
            this.retFem = RetFem;
            this.minMales = MinMales;
            this.retMal = RetMal;
            this.tMNeeded = TMNeeded;
            this.femNeeded = FemNeeded;
            this.malNeed = MalNeed;
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

}

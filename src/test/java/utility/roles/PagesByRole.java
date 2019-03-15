/**
 * Created by Miguel Angel Aguilar
 * maaguilar@sciodev.com - mar 2019
 **/

package utility.roles;

import org.apache.bcel.generic.RET;
import org.openqa.selenium.WebDriver;
import pages.reports.ReportPage;
import pages.reports.coordinator.captainContactListImport_page;
import pages.reports.coordinator.captainContactList_page;
import pages.reports.coordinator.defaultList_page;
import pages.reports.divisionAssignment.badTeamCountInDivision_page;
import pages.reports.divisionAssignment.teamsByDivision_page;
import pages.reports.email.leagueExtractEmailList_page;
import pages.reports.email.profileExtractEmailList_page;
import pages.reports.facilities.activeFacilitiesBreakdown_page;
import pages.reports.juniorChallengeLadder.*;
import pages.reports.leveling.leagueAudit_page;
import pages.reports.leveling.levelAdjustment_page;
import pages.reports.leveling.preliminaryPlacement_page;
import pages.reports.leveling.viewCurrentRanking_page;
import pages.reports.membership.suspendedMembers_page;
import pages.reports.membership.tallyReport_page;
import pages.reports.paymentRecords.duesReconciliation_page;
import pages.reports.paymentRecords.purchaseDetail_page;
import pages.reports.paymentRecords.tournamentPayments;
import pages.reports.playoffDraw.*;
import pages.reports.roster.*;
import pages.reports.scheduling.seasonSchedule_page;

import java.util.ArrayList;
import java.util.List;

public enum PagesByRole {

    COORDINATOR {
        @Override
        public List<ReportPage> getPages(WebDriver driver) {
            List<ReportPage> list = new ArrayList<>();
            list.addAll(getCoordinatorPages(driver));
            list.addAll(getJCLPages(driver));
            return list;
        }
    },
    IT_COMMITTEE {
        @Override
        public List<ReportPage> getPages(WebDriver driver) {
            List<ReportPage> list = new ArrayList<>();
            list.addAll(getCoordinatorPages(driver));
            list.addAll(getDivisionAssignmentPages(driver));
            list.addAll(getEmailPages(driver));
            list.addAll(getFacilitiesPages(driver));
            list.addAll(getJCLPages(driver));
            list.addAll(getLevelingPages(driver));
            list.addAll(getMembershipPages(driver));
            list.addAll(getRosterPages(driver));
            list.addAll(getSchedulingPages(driver));
            return list;
        }

    },
    LEAGUE_VP {
        @Override
        public List<ReportPage> getPages(WebDriver driver) {
            List<ReportPage> list = new ArrayList<>();
            list.addAll(getCoordinatorPages(driver));
            list.addAll(getDivisionAssignmentPages(driver));
            list.addAll(getEmailPages(driver));
            list.addAll(getFacilitiesPages(driver));
            list.addAll(getJCLPages(driver));
            list.addAll(getLevelingPages(driver));
            list.addAll(getMembershipPages(driver));
            list.addAll(getRosterPages(driver));
            list.addAll(getSchedulingPages(driver));
            return list;
        }
    },
    OVERALL_COORDINATOR {
        @Override
        public List<ReportPage> getPages(WebDriver driver) {
            List<ReportPage> list = new ArrayList<>();
            list.addAll(getCoordinatorPages(driver));
            list.addAll(getLevelingPages(driver));
            list.addAll(getRosterPages(driver));
            list.addAll(getSchedulingPages(driver));
            return list;
        }
    },
    PRESIDENT {
        @Override
        public List<ReportPage> getPages(WebDriver driver) {
            List<ReportPage> list = new ArrayList<>();
            list.addAll(getEmailPages(driver));
            list.addAll(getFacilitiesPages(driver));
            list.addAll(getMembershipPages(driver));
            list.addAll(getSchedulingPages(driver));
            return list;
        }
    },
    JUNIOR_LADDER_MANAGER {
        @Override
        public List<ReportPage> getPages(WebDriver driver) {
            List<ReportPage> list = new ArrayList<>();
            list.addAll(getJCLPages(driver));
            list.addAll(getSchedulingPages(driver));
            return list;
        }
    },
    VP_Jr_CHALLENGE_LADDER {
        @Override
        public List<ReportPage> getPages(WebDriver driver) {
            List<ReportPage> list = new ArrayList<>();
            list.addAll(getJCLPages(driver));
            list.addAll(getSchedulingPages(driver));
            return list;
        }
    };

    public abstract List<ReportPage> getPages(WebDriver driver);

    private static List<ReportPage> getCoordinatorPages(WebDriver driver) {
        List<ReportPage> list = new ArrayList<>();
        list.add(new captainContactList_page(driver));
        list.add(new captainContactListImport_page(driver));
        list.add(new defaultList_page(driver));
        return list;
    }

    private static List<ReportPage> getDivisionAssignmentPages(WebDriver driver) {
        List<ReportPage> list = new ArrayList<>();
        list.add(new badTeamCountInDivision_page(driver));
        list.add(new teamsByDivision_page(driver));
        return list;
    }

    private static List<ReportPage> getEmailPages(WebDriver driver) {
        List<ReportPage> list = new ArrayList<>();
        list.add(new leagueExtractEmailList_page(driver));
        list.add(new profileExtractEmailList_page(driver));
        return list;
    }

    private static List<ReportPage> getFacilitiesPages(WebDriver driver) {
        List<ReportPage> list = new ArrayList<>();
        list.add(new activeFacilitiesBreakdown_page(driver));
        return list;
    }

    private static List<ReportPage> getJCLPages(WebDriver driver) {
        List<ReportPage> list = new ArrayList<>();
        list.add(new jcAwardSportmanship_page(driver));
        list.add(new jcAwardSweatshirt_page(driver));
        list.add(new jcAwardTournamentAssistance_page(driver));
        list.add(new jcAwardTowelRecipients_page(driver));
        list.add(new jcAwardTshirt_page(driver));
        list.add(new ladderPlayerContactListImport_page(driver));
        list.add(new ladderPlayerContactList_page(driver));
        list.add(new ladderReport_page(driver));
        list.add(new ladderReportImport_page(driver));
        return list;
    }

    private static List<ReportPage> getLevelingPages(WebDriver driver) {
        List<ReportPage> list = new ArrayList<>();
        list.add(new leagueAudit_page(driver));
        list.add(new levelAdjustment_page(driver));
        list.add(new preliminaryPlacement_page(driver));
        list.add(new viewCurrentRanking_page(driver));
        return list;
    }

    private static List<ReportPage> getMembershipPages(WebDriver driver) {
        List<ReportPage> list = new ArrayList<>();
        list.add(new suspendedMembers_page(driver));
        list.add(new tallyReport_page(driver));
        return list;
    }

    private static List<ReportPage> getRosterPages(WebDriver driver) {
        List<ReportPage> list = new ArrayList<>();
        list.add(new playerAdds_page(driver));
        list.add(new requestForReview_page(driver));
        list.add(new returningTeamsPercentage_page(driver));
        list.add(new teamRegistrationTotals_page(driver));
        list.add(new teamsByFacility_page(driver));
        list.add(new teamsByLeague_page(driver));
        list.add(new teamsWithoutMinimumNumberOfPlayers_page(driver));
        list.add(new VRSTeamRosters_page(driver));
        return list;
    }

    private static List<ReportPage> getSchedulingPages(WebDriver driver) {
        List<ReportPage> list = new ArrayList<>();
        list.add(new seasonSchedule_page(driver));
        return list;

    }

    private static List<ReportPage> getAdministration(WebDriver driver) { //payment records
        List<ReportPage> list = new ArrayList<>();
        list.add(new duesReconciliation_page(driver));
        list.add(new purchaseDetail_page(driver));
        list.add(new tournamentPayments(driver));
        return list;
    }

    private static List<ReportPage> getPlayOffDraw(WebDriver driver) {
        List<ReportPage> list = new ArrayList<>();
        list.add(new bagTagMailingList_page(driver));
        list.add(new bagTagProcess_page(driver));
        list.add(new finalStandingsByLeague_page(driver));
        list.add(new finalStandingsByLeagueWorksheet_page(driver));
        list.add(new maxPlayoffTeamMembers_page(driver));
        return list;
    }

}

/**
 * Created by Miguel Angel Aguilar
 * maaguilar@sciodev.com - feb 2019
 **/

package utility.roles;

public class RolesPermissions {
    private String roleName;
    private boolean administration;
    private boolean coordinator;
    private boolean divisionAssignment;
    private boolean email;
    private boolean facilities;
    private boolean juniorChallenge;
    private boolean leveling;
    private boolean membership;
    private boolean playoffDraw;
    private boolean roster;
    private boolean scheduling;
    private boolean tournaments;

    public RolesPermissions(String roleName, boolean administration, boolean coordinator, boolean divisionAssignment, boolean email,
                            boolean facilities, boolean juniorChallenge, boolean leveling, boolean membership, boolean playoffDraw,
                            boolean roster, boolean scheduling, boolean tournaments) {
        this.roleName = roleName;
        this.administration = administration;
        this.coordinator = coordinator;
        this.divisionAssignment = divisionAssignment;
        this.email = email;
        this.facilities = facilities;
        this.juniorChallenge = juniorChallenge;
        this.leveling = leveling;
        this.membership = membership;
        this.playoffDraw = playoffDraw;
        this.roster = roster;
        this.scheduling = scheduling;
        this.tournaments = tournaments;
    }

    public RolesPermissions(String roleName, boolean[] permissions) {
        this(roleName, permissions[0], permissions[1], permissions[2], permissions[3], permissions[4], permissions[5], permissions[6],
                permissions[7], permissions[8], permissions[9], permissions[10], permissions[11]);
    }

    public boolean isAdministration() {
        return administration;
    }

    public boolean isCoordinator() {
        return coordinator;
    }

    public boolean isDivisionAssignment() {
        return divisionAssignment;
    }

    public boolean isEmail() {
        return email;
    }

    public boolean isFacilities() {
        return facilities;
    }

    public boolean isJuniorChallenge() {
        return juniorChallenge;
    }

    public boolean isLeveling() {
        return leveling;
    }

    public boolean isMembership() {
        return membership;
    }

    public boolean isPlayoffDraw() {
        return playoffDraw;
    }

    public boolean isRoster() {
        return roster;
    }

    public boolean isScheduling() {
        return scheduling;
    }

    public boolean isTournaments() {
        return tournaments;
    }



}

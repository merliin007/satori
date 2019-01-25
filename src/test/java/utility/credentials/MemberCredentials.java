package utility.credentials;

public class MemberCredentials {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public MemberCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MemberCredentials() {
        this.username = "OfficeUser";
        this.password = "Abc!123";
    }


}

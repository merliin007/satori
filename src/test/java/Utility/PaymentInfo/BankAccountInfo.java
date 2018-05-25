package utility.paymentInfo;

public class BankAccountInfo {
    private String accountType;
    private String routingNumber;
    private String accountNumber;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    public String getAccountType() {
        return accountType;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public BankAccountInfo(String accountType, String routingNumber, String accountNumber, String firstName, String lastName, String streetAddress, String city, String state, String zipCode) {
        this.accountType = accountType;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public BankAccountInfo() {
        this("Saving", "071101307", "123456789", "QA", "Automation",
                "101 Sococo Virtual Office", "Tucson", "Arizona", "85775");
    }
}

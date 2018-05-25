package utility.paymentInfo;

public class CheckInfo {
    private String checkNumber;
    private String accountHolder;

    public CheckInfo(String checkNumber, String accountHolder) {
        this.checkNumber = checkNumber;
        this.accountHolder = accountHolder;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public CheckInfo() {
        this(String.valueOf(System.currentTimeMillis()), "QA Automator");
    }
}

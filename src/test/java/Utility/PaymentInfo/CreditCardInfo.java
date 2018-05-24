package Utility.PaymentInfo;

public class CreditCardInfo {
    private String ccNumber;
    private String ccHolder;
    private String ccMonth;
    private String ccYear;
    private String ccCVV;

    public String getCcNumber() {
        return ccNumber;
    }

    public String getCcHolder() {
        return ccHolder;
    }

    public String getCcMonth() {
        return ccMonth;
    }

    public String getCcYear() {
        return ccYear;
    }

    public String getCcCVV() {
        return ccCVV;
    }

    public CreditCardInfo(String ccNumber, String ccHolder, String ccMonth, String ccYear, String ccCVV) {
        this.ccNumber = ccNumber;
        this.ccHolder = ccHolder;
        this.ccMonth = ccMonth;
        this.ccYear = ccYear;
        this.ccCVV = ccCVV;
    }

    public CreditCardInfo() {
        this("4111111111111111", "QA Automation Testing", "10", "2020", "123");
    }
}

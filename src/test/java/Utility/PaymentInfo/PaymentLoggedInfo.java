package Utility.PaymentInfo;

import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class PaymentLoggedInfo {
    private WebElement lnkSelect;
    private WebElement lnkDelete;
    private WebElement lnkRefund;
    private String paymentType;
    private String paymentId;
    private LocalDateTime paymentDate;
    private String paymentMethod;
    private String status;
    private String amount;

    public PaymentLoggedInfo() {}

    public PaymentLoggedInfo(WebElement lnkSelect, WebElement lnkDelete, WebElement lnkRefund, String paymentType,
                             String paymentId, LocalDateTime paymentDate, String paymentMethod, String status, String amount) {
        this.lnkSelect = lnkSelect;
        this.lnkDelete = lnkDelete;
        this.lnkRefund = lnkRefund;
        this.paymentType = paymentType;
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.amount = amount;
    }

    public PaymentLoggedInfo(ArrayList<WebElement> cells){
        this(cells.get(0), cells.get(1), cells.get(2), cells.get(3).getText(),
                cells.get(4).getText(), LocalDateTime.parse(cells.get(5).getText(), DateTimeFormatter.ofPattern("M/dd/yyyy h:mm:ss a")),
                cells.get(6).getText(), cells.get(7).getText(), cells.get(8).getText());
    }

    public WebElement getLnkSelect() {
        return lnkSelect;
    }

    public WebElement getLnkDelete() {
        return lnkDelete;
    }

    public WebElement getLnkRefund() {
        return lnkRefund;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public String getAmount() {
        return amount;
    }
}

/*
 * Created by Miguel Angel Aguilar Cuevas
 * 18/01/2019 at 12:10 PM
 */
package utility.payment;

import java.util.List;

public class Refund {
    private String refundMethod;
    private String date;
    private String comments;
    private String amount;

    public Refund(List<String> r) {
        refundMethod = r.get(0);
        date = r.get(1);
        comments = r.get(2);
        amount = r.get(3);
    }

    public String getRefundMethod() {
        return refundMethod;
    }

    public String getDate() {
        return date;
    }

    public String getCommentsWithTime() {
        return comments + " " + System.currentTimeMillis();
    }

    public String getComments() {
        return comments;
    }

    public String getAmount() {
        return amount;
    }
}

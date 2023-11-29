package edu.ansvistunov.dto;

import edu.ansvistunov.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentDto {
    private int paymentNumber;
    private int policyNumber;
    private double paymentAmount;
    private Date paymentDate;

    public int getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(int paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public int getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(int policyNumber) {
        this.policyNumber = policyNumber;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return String.format("Номер выплаты: %d \n Номер полиса: %d \n Сумма выплаты: %.2f \n Дата выплаты: %s",
                paymentNumber, policyNumber, paymentAmount, Util.dateFormat(paymentDate));
    }
}

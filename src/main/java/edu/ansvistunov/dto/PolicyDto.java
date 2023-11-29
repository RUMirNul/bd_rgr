package edu.ansvistunov.dto;

import edu.ansvistunov.Util;

import java.util.Date;

public class PolicyDto {
    private int policyNumber;
    private int clientNumber;
    private int insuranceCode;
    private int companyKpp;
    private Date startDate;
    private Date endDate;

    public int getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(int policyNumber) {
        this.policyNumber = policyNumber;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public int getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(int insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public int getCompanyKpp() {
        return companyKpp;
    }

    public void setCompanyKpp(int companyKpp) {
        this.companyKpp = companyKpp;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("Номер полиса: %d \n Номер клиента: %d \n Код вида страхования: %d \n КПП компании: %d \n " +
                "Дата начала действия полиса: %s \n Дата конца действия полиса: %s \n",
                policyNumber, clientNumber, insuranceCode, companyKpp, Util.dateFormat(startDate), Util.dateFormat(endDate));

    }
}

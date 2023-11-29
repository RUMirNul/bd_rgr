package edu.ansvistunov.dto;

public class CompanyDto {
    private int companyKpp;
    private String name;
    private String address;

    public int getCompanyKpp() {
        return companyKpp;
    }

    public void setCompanyKpp(int companyKpp) {
        this.companyKpp = companyKpp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("КПП компании: %d \n Название компании: %s \n Адрес компании: %s", companyKpp, name, address);
    }
}

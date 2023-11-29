package edu.ansvistunov.dto;

public class ClientDto {
    private int clientNumber;
    private String fullName;
    private String address;
    private String phoneNumber;

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("Номер клиента: %d \n ФИО: %s \n Адрес: %s \n Телефон: %s", clientNumber, fullName, address, phoneNumber);
    }
}

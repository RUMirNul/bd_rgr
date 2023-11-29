package edu.ansvistunov.dto;

public class InsuranceTypeDto {
    private int insuranceCode;
    private String name;
    private String description;

    public int getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(int insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Код вида страхования: %d \n Название типа страхования: %s \n Описание: %s", insuranceCode, name, description);
    }
}

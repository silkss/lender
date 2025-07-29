package ru.testtask.lender.types;

public enum MaritalStatus {
    MARRIED("в браке"),
    SINGLE("холост");

    private final String displayValue;

    private MaritalStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

package org.example.reportservice;

public enum ReportFormat {
    EXCEL(1),TEXT(2), RAW_TEXT(3);

    private int value;

    ReportFormat(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

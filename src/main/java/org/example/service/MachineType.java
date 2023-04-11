package org.example.service;

public enum MachineType {
    COORDINATE (1), OPTICAL (2), MICROSCOPE(3);

    private int number;

    MachineType(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

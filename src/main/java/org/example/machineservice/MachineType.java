package org.example.machineservice;

public enum MachineType {
    COORDINATE (1), OPTICAL (2), MICROSCOPE(3);

    private int value;

    MachineType(int number) {
        this.value = number;
    }

    public int getValue() {
        return value;
    }
}

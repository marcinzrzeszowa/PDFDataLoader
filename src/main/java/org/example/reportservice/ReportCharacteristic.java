package org.example.reportservice;

public class ReportCharacteristic{

    private String name;
    private Double actual;
    private Double nominal;
    private Double difference;

    public String name() {
        return name;
    }

    public Double actual() {
        return actual;
    }

    public Double nominal() {
        return nominal;
    }

    public Double difference() {
        return difference;
    }

    public ReportCharacteristic(String name, Double actual, Double nominal, Double difference){
        this.name = name;
        this.actual = actual;
        this.nominal = nominal;
        this.difference = difference;
    }

    public ReportCharacteristic(){
        this("",0.0d, 0.0d, 0.0d);
    }

}

package org.example.report;

public record ReportCharacteristic(String name, Double actual, Double nominal, Double difference ) {

    public ReportCharacteristic(){
        this("",0.0d, 0.0d, 0.0d);
    }

}

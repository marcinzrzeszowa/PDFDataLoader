package org.example.reportservice;

record ReportHeader(String componentName,
                           String date,
                           String drawingNumber,
                           String partNumber1,
                           String partNumber2,
                           String programName,
                           String operatorName) {

    public ReportHeader(){
        this("", "","","","","","");
    }

}

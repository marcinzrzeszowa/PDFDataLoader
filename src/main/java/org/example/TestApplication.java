package org.example;

import org.example.structure.*;

import java.io.IOException;


public class TestApplication implements Application {
    private MeasurementReport measurementReport;
    private StringBuilder rawInputText;



    @Override
    public Return startApplication() throws IOException {

        rawInputText = DataReader.readFile("optic_report.pdf");

        measurementReport = new CoordinateMeasuringMachineReport(rawInputText);

        //measurementReport.getReportHeader();

        String rawReport = measurementReport.extractRawTextFile(rawInputText);

        System.out.println(rawReport);
        System.out.println("----------------------");

       //measurementReport.getReportAttributesList();





        return new Return.ResultBuilder(true).build();
    }





}

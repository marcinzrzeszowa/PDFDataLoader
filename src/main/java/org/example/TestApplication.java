package org.example;

import org.example.machine.CoordinateMeasuringMachineReport;
import org.example.machine.MachineReport;
import org.example.machine.MachineType;
import org.example.machine.OpticalMeasuringMachineReport;
import org.example.report.*;
import org.example.structure.*;

import java.io.IOException;


public class TestApplication implements Application {
    private MachineReport machine;

    @Override
    public Return startApplication() throws IOException {

        String filePath = "E:\\Programowanie\\Projekty\\PDFDataLoader\\src\\main\\resources\\cmm_report.pdf";

        machine = selectMachine(MachineType.CMM);
        machine.createReport(filePath, ReportFormat.EXCEL);


        return new Return.ResultBuilder(true).build();
    }

    private MachineReport selectMachine(MachineType type){
        switch (type){
            case CMM:
                machine = new CoordinateMeasuringMachineReport();
                break;
            case OPTIC:
                machine = new OpticalMeasuringMachineReport();
                break;
        }
        return machine;
    }
}

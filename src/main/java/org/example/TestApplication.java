package org.example;

import org.example.machine.CMMachineReport;
import org.example.machine.MachineReport;
import org.example.machine.MachineType;
import org.example.machine.OMMachineReport;
import org.example.report.*;
import org.example.structure.*;

import java.io.IOException;


public class TestApplication implements Application {
    private MachineReport machine;

    @Override
    public Return startApplication() throws IOException {

        String filePath = "E:\\Programowanie\\Projekty\\PDFDataLoader\\src\\main\\resources\\cmm_report.pdf";
        String filePath2 = "src\\main\\resources\\CMM_1.pdf";

        machine = selectMachine(MachineType.COORDINATE);
        machine.createReport(filePath, ReportFormat.TXT);


        return new Return.ResultBuilder(true).build();
    }

    private MachineReport selectMachine(MachineType type){
        switch (type){
            case COORDINATE:
                machine = new CMMachineReport();
                break;
            case OPTICAL:
                machine = new OMMachineReport();
                break;
        }
        return machine;
    }
}

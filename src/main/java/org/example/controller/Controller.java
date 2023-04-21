package org.example.controller;

import org.example.reportservice.ReportService;
import org.example.machineservice.Machine;
import org.example.machineservice.MachineType;
import org.example.reportservice.ReportFormat;
import org.example.repository.InputFileExtractor;
import org.example.view.ViewActionController;

public class Controller implements ViewActionController{
    private Machine machine;
    private ReportService report;
    private MachineType machineType;
    private ReportFormat reportFormat;
    private InputFileExtractor inputFileManager;

    public Controller() {
        inputFileManager = InputFileExtractor.getInstance();
        report = new ReportService();
    }


    @Override
    public boolean convertFileAction(String inputFilePath, String selectedMachineStrNr, String selectedReportStrNr) {

       /*

       //CMM
        report.createReport(
                "E:\\Programowanie\\Projekty\\PDFDataLoader\\src\\main\\resources\\CMM_1.pdf",
                ReportFormat.TEXT,
                Machine.MachineInstance(MachineType.COORDINATE));
        return true;*/

       /*

       //OPTIC
       report.createReport(
                "E:\\Programowanie\\Projekty\\PDFDataLoader\\src\\main\\resources\\optic_report.pdf",
                ReportFormat.TEXT,
                Machine.MachineInstance(MachineType.OPTICAL));
        return true;
        */

        /*
        report.createReport(
                "E:\\Programowanie\\Projekty\\PDFDataLoader\\src\\main\\resources\\m1.pdf",
                ReportFormat.TEXT,
                Machine.MachineInstance(MachineType.MICROSCOPE));
        return true;*/


        //Replace by test data
        machineType = chooseMachineType(selectedMachineStrNr);
        reportFormat = chooseReportFormat(selectedReportStrNr);

        if(fileIsValid(inputFilePath) && machineType != null && reportFormat != null){
            machine = Machine.MachineInstance(machineType);
            report.createReport(inputFilePath, reportFormat, machine);
            return true;
        }
        return false;
        //
    }

    private ReportFormat chooseReportFormat(String selectedReportStrNr) {
        final int TEXT = 2;
        final int EXEL = 1;
        final int RAW_TEXT = 3;
        ReportFormat reportFormat;

        int machineSelected = Integer.valueOf(selectedReportStrNr);
        switch (machineSelected){
            case TEXT:
                reportFormat = ReportFormat.TEXT;
                break;
            case EXEL:
                reportFormat = ReportFormat.EXCEL;
                break;
            default:
                reportFormat = ReportFormat.RAW_TEXT;
                break;
        }
        return reportFormat;
    }

    private MachineType chooseMachineType(String selectedMachineStrNr) {
        final int COORDINATE = 1;
        final int OPTIC = 2;
        final int MICROSCOPE = 3;
        MachineType machineType = null;

        int machineSelected = Integer.valueOf(selectedMachineStrNr);
        switch (machineSelected){
            case COORDINATE:
                machineType = MachineType.COORDINATE;
                break;
            case OPTIC:
                machineType = MachineType.OPTICAL;
                break;
            case MICROSCOPE:
                machineType = MachineType.MICROSCOPE;
                break;
        }
        return machineType;
    }

    private boolean fileIsValid(String userFilePath) {
        return inputFileManager.inputFileIsValid(userFilePath);
    }


}

package org.example.controller;

import org.example.service.MachineService;
import org.example.service.MachineType;
import org.example.report.ReportFormat;
import org.example.view.ViewActionController;

public class ActionController implements ViewActionController {
    private MachineService machineService;


    public ActionController() {
    }

    public boolean convertFileAction(String userFilePath, String selectedMachineStrNr, String selectedReportStrNr) {
        final int COORDINATE = 1;
        final int OPTIC = 2;
        final int MICROSCOPE = 3;
        final int TEXT = 2;
        final int EXEL = 1;
        final int RAW_TEXT = 3;
        boolean result = false;

        if(!fileIsValid(userFilePath)){
            int machineSelected = Integer.valueOf(selectedMachineStrNr);
            switch (machineSelected) {
                case COORDINATE -> machineService = MachineService.MachineInstance(MachineType.COORDINATE);
                case OPTIC -> machineService = MachineService.MachineInstance(MachineType.OPTICAL);
                case MICROSCOPE -> machineService = MachineService.MachineInstance(MachineType.MICROSCOPE);
                default -> throw new IllegalStateException("Unexpected value: " + machineSelected);
            }

            int reportSelected = Integer.valueOf(selectedReportStrNr);
            switch (reportSelected){
                case TEXT -> machineService.createReport(userFilePath, ReportFormat.TEXT);
                case EXEL-> machineService.createReport(userFilePath, ReportFormat.EXCEL);
                case RAW_TEXT -> machineService.createReport(userFilePath, ReportFormat.RAW_TEXT);
            }
        }
        return result;
    }

    private boolean fileIsValid(String userFilePath) {

        return false;
    }

}

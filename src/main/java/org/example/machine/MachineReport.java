package org.example.machine;

import org.example.files.InputFileManager;
import org.example.files.OutputFileManager;
import org.example.report.ReportCharacteristic;
import org.example.report.ReportFormat;

import java.util.ArrayList;
import java.util.List;

public abstract class MachineReport implements CharacteristicValidator{
    private InputFileManager inputFileManager;
    private OutputFileManager outputFileManager;
    private List<ReportCharacteristic> reportCharacteristicList;

    public MachineReport(){
        this.inputFileManager = InputFileManager.getInstance();
        this.outputFileManager = OutputFileManager.getInstance();
        this.reportCharacteristicList = new ArrayList<>();
    }

    //Template method
    public final void createReport(String filePath, ReportFormat reportFormat){
            reportCharacteristicList = createCharacteristics(filePath);
            writeReport(filePath, reportFormat, reportCharacteristicList);
    }

    private List<ReportCharacteristic> createCharacteristics(String parsedFile){
        return inputFileManager.createCharacteristics(parsedFile, this);
    }

    public static MachineReport MachineInstance(MachineType type){
        switch (type){
            case COORDINATE:
                return new CMMachineReport();
            case OPTICAL:
                return new OMMachineReport();
        }
        return null;
    }

    private void writeReport(String filePath, ReportFormat reportFormat, List<ReportCharacteristic> characteristicList){
        switch (reportFormat){
            case TXT:
                outputFileManager.writeTXTReport(filePath, characteristicList);
                break;
            case EXCEL:
                outputFileManager.writeXLSXReport(filePath, characteristicList);
                break;
            case RAW_TXT:
                outputFileManager.writeRawTXTFile(filePath);
                break;

        }
    }

}

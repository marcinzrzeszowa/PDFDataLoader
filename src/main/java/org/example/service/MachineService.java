package org.example.service;

import org.example.repository.InputFileManager;
import org.example.repository.OutputFileManager;
import org.example.report.ReportCharacteristic;
import org.example.report.ReportFormat;

import java.util.ArrayList;
import java.util.List;

public abstract class MachineService implements MachineServiceRequirements {
    private InputFileManager inputFileManager;
    private OutputFileManager outputFileManager;
    private List<ReportCharacteristic> reportCharacteristicList;

    public MachineService(){
        this.inputFileManager = InputFileManager.getInstance();
        this.outputFileManager = OutputFileManager.getInstance();
        this.reportCharacteristicList = new ArrayList<>();
    }

    public final void createReport(String filePath, ReportFormat reportFormat){
            if(!reportFormat.equals(ReportFormat.RAW_TEXT)){
                reportCharacteristicList = createCharacteristics(filePath);
            }
            writeReport(filePath, reportFormat, reportCharacteristicList);
    }

    private List<ReportCharacteristic> createCharacteristics(String parsedFile){
        return inputFileManager.createCharacteristics(parsedFile, this);
    }

    public static MachineService MachineInstance(MachineType type){
        switch (type){
            case COORDINATE:
                return new CMMachineService();
            case OPTICAL:
                return new OMMachineService();
        }
        return null;
    }

    private void writeReport(String filePath, ReportFormat reportFormat, List<ReportCharacteristic> characteristicList){
        switch (reportFormat){
            case TEXT:
                outputFileManager.writeTXTReport(filePath, characteristicList);
                break;
            case EXCEL:
                outputFileManager.writeXLSXReport(filePath, characteristicList);
                break;
            case RAW_TEXT:
                outputFileManager.writeRawTXTFile(filePath);
                break;
        }
    }

}

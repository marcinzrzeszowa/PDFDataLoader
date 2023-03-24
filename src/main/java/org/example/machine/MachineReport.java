package org.example.machine;

import org.example.files.InputFileManager;
import org.example.files.OutputFileManager;
import org.example.report.ReportCharacteristic;
import org.example.report.ReportFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class MachineReport{
    private InputFileManager inputFileManager;
    private OutputFileManager outputFileManager;
    private List<ReportCharacteristic> reportCharacteristicList;

    protected CharacteristicValidator characteristicValidator;

    public MachineReport(){
        this.inputFileManager = InputFileManager.getInstance();
        this.outputFileManager = OutputFileManager.getInstance();
        this.reportCharacteristicList = new ArrayList<>();
        this.characteristicValidator = setClassCharacteristicValidator();
    }

    public final void createReport(String filePath, ReportFormat reportFormat){
            reportCharacteristicList = parseFile(filePath);
            writeReport(filePath, reportFormat, reportCharacteristicList);
    }

    //Extract characteristics with values from parsed file and save to field List<ReportCharacteristic>
    public abstract List<ReportCharacteristic> extractReportCharacteristics(StringBuilder file, InputFileManager fileManager) throws IOException;

    //Set new CharacteristicValidator implementation class with regex rules for characteristics
    protected abstract CharacteristicValidator setClassCharacteristicValidator();

    private List<ReportCharacteristic> parseFile(String path){
        return inputFileManager.createCharacteristic(path, this);
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
        }
    }
}

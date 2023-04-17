package org.example.reportservice;

import org.example.machineservice.Machine;
import org.example.repository.InputFileExtractor;
import org.example.repository.OutputFileExtractor;

import java.util.ArrayList;
import java.util.List;

public class ReportService {
    private InputFileExtractor inputFileManager;
    private OutputFileExtractor outputFileManager;
    private List<ReportCharacteristic> reportCharacteristicList;

    public ReportService() {
        this.inputFileManager = InputFileExtractor.getInstance();
        this.outputFileManager = OutputFileExtractor.getInstance();
        this.reportCharacteristicList = new ArrayList<>();
    }

    public final void createReport(String filePath, ReportFormat reportFormat, Machine machineService){
        if(!reportFormat.equals(ReportFormat.RAW_TEXT)){
            reportCharacteristicList = createCharacteristics(machineService, filePath);
        }
        writeReport(filePath, reportFormat, reportCharacteristicList, outputFileManager );
    }

    private List<ReportCharacteristic> createCharacteristics(Machine machine, String parsedFile){
        return inputFileManager.createCharacteristics(parsedFile, machine);
    }

    private void writeReport(String filePath, ReportFormat reportFormat, List<ReportCharacteristic> characteristicList, OutputFileExtractor outputFileManager){
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
